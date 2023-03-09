package com.cashier.app.cashierApp.Controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cashier.app.cashierApp.Model.ResponseHandler;
import com.cashier.app.cashierApp.Model.Entity.Item;
import com.cashier.app.cashierApp.Model.Request.ItemRequest;
import com.cashier.app.cashierApp.Projection.ItemView;
import com.cashier.app.cashierApp.Repository.ItemRepository;

@Controller
@RequestMapping("/api")
public class ItemController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/testconnection")
    public ResponseEntity<Object> index(){
        return ResponseHandler.generateResponse("Server up and running!", HttpStatus.OK, null);
    }

    //inserting item
    @CrossOrigin
    @PostMapping(value = "/item", consumes = {"application/json"})
    public ResponseEntity<Object> addItem(@RequestBody ItemRequest itemRequest){
        try {
            //Initialize required response data
            //http status
            //message
            //data
            HttpStatus status = HttpStatus.OK;
            String message="";

            //validation is there any data with same name and uuid in the database
            LocalDateTime localDateTime = LocalDateTime.now();
            Item itemCheckByName = itemRepository.findByItemName(itemRequest.getItemName());

            UUID uuid = UUID.randomUUID();
            String uuidAsString = uuid.toString();
            Item itemCheckByUuid = itemRepository.findOneByUuid(uuidAsString);

            //if validation variable is null, then add data to database
            //else return validation message
            if(itemCheckByName == null && itemCheckByUuid == null){
                Item newItem = new Item(
                    itemRequest.getItemName(),
                    itemRequest.getItemPrice(),
                    itemRequest.getItemQty(),
                    localDateTime.toString(),
                    localDateTime.toString(),
                    itemRequest.getUnitTypeId(),
                    uuidAsString);

                newItem = itemRepository.save(newItem);
                message = "Data succesfully added";
                status = HttpStatus.CREATED;
            }else{
                message = "Failed add data, data already in database";
                status = HttpStatus.INTERNAL_SERVER_ERROR;
            }
            return ResponseHandler.generateResponse(message, status, itemRequest);
        } catch (Exception e) {
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @CrossOrigin
    @GetMapping("/getallitem")
    public ResponseEntity<Object> getItem(){
        try {
            List<ItemView> responseData = itemRepository.getItemView();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, responseData);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //edit item
    @CrossOrigin
    @PutMapping(value = "/item", consumes = {"application/json"})
    public ResponseEntity<Object> updateItem(@RequestBody ItemRequest itemRequest){
        //validation input
        // {
        //     uuid:xxxx,
        //     itemPrice: 2000,
        //     itemQty:3,
        //     unitTypeId:1
        // }
        try {
            String inputUUID = itemRequest.getUUID();

            LocalDateTime localDateTime = LocalDateTime.now();
            if(inputUUID == null){
                return ResponseHandler.generateResponse("UUID is required", HttpStatus.BAD_REQUEST, null);
            }
            Item oldItem = itemRepository.findOneByUuid(inputUUID);
            
            oldItem.setItemPrice((itemRequest.getItemPrice()!=null) ? itemRequest.getItemPrice() : oldItem.getItemPrice());
            oldItem.setItemQty((itemRequest.getItemQty()!=null) ? itemRequest.getItemQty() : oldItem.getItemQty());
            oldItem.setUnitTypeId((itemRequest.getUnitTypeId()!=null) ? itemRequest.getUnitTypeId() : oldItem.getUnitTypeId());
            oldItem.setUpdatedAt(localDateTime.toString());

            oldItem = itemRepository.save(oldItem);
            return ResponseHandler.generateResponse("Update Success", HttpStatus.OK, oldItem);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    //delete
    @CrossOrigin
    @DeleteMapping("/item")
    public ResponseEntity<Object> deleteItem(@RequestBody ItemRequest itemRequest){
        try {
            String inputUUID = itemRequest.getUUID();
            if(inputUUID == null){
                return ResponseHandler.generateResponse("UUID is required", HttpStatus.BAD_REQUEST, null);
            }
            Item deletedItem = itemRepository.findOneByUuid(inputUUID);
    
            itemRepository.delete(deletedItem);

            return ResponseHandler.generateResponse("Delete Success", HttpStatus.OK, null);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
