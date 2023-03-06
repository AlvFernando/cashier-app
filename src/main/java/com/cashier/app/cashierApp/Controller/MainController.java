package com.cashier.app.cashierApp.Controller;

import java.time.Instant;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cashier.app.cashierApp.Model.Item;
import com.cashier.app.cashierApp.Model.ItemRequest;
import com.cashier.app.cashierApp.Model.ResponseHandler;
import com.cashier.app.cashierApp.Projection.ItemView;
import com.cashier.app.cashierApp.Repository.ItemRepository;

@Controller
@RequestMapping("/api")
public class MainController {
    @Autowired
    private ItemRepository itemRepository;

    @GetMapping("/testconnection")
    public ResponseEntity<Object> index(){
        return ResponseHandler.generateResponse("Server up and running!", HttpStatus.OK, null);
    }

    //inserting item
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
            Instant instant = Instant.now();
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
                    instant.toString(),
                    instant.toString(),
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

    //get item list
    @GetMapping("/item")
    public ResponseEntity<Object> getItem(){
        try {
            List<ItemView> responseData = itemRepository.getItemView();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, responseData);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse("Error", HttpStatus.MULTI_STATUS, null);
        }
    }

    //edit item

    //delete item
}
