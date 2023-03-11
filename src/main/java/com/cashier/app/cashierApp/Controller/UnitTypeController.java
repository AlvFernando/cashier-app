package com.cashier.app.cashierApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cashier.app.cashierApp.Model.ResponseHandler;
import com.cashier.app.cashierApp.Model.Entity.UnitType;
import com.cashier.app.cashierApp.Repository.UnitTypeRepository;

@Controller
@RequestMapping("/api")
public class UnitTypeController {
    @Autowired
    UnitTypeRepository unitTypeRepository;

    @CrossOrigin
    @GetMapping("/unittype")
    public ResponseEntity<Object> getUnitType(){
        try {
            Iterable<UnitType> responseData = unitTypeRepository.findAll();
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, responseData);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @CrossOrigin
    @PostMapping("/unittype")
    public ResponseEntity<Object> addUnitType(@RequestBody UnitType unitType){
        try {
            //validation input
            if(unitType.getUnitType() == null){
                return ResponseHandler.generateResponse("Unit Type input name is null", HttpStatus.BAD_REQUEST, unitType);
            }
            if(unitType.getUnitType().isEmpty() || unitType.getUnitType().trim().isEmpty()){
                return ResponseHandler.generateResponse("Unit Type input name is empty", HttpStatus.BAD_REQUEST, unitType);
            }
            //validation data
            UnitType unitTypeCheck = unitTypeRepository.findOneByUnitType(unitType.getUnitType());
            if(unitTypeCheck != null){
                return ResponseHandler.generateResponse("Unit Type data already exist in database", HttpStatus.INTERNAL_SERVER_ERROR, null);
            }

            //add data to database
            UnitType responseData = unitTypeRepository.save(unitType);

            return ResponseHandler.generateResponse("Success", HttpStatus.CREATED, responseData);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @CrossOrigin
    @DeleteMapping("/unittype")
    public ResponseEntity<Object> deleteUnitType(@RequestBody UnitType unitType){
        try {
            //validation input
            if(unitType.getId() == null){
                return ResponseHandler.generateResponse("Unit Type input id is null", HttpStatus.BAD_REQUEST, unitType);
            }

            //delete item if exist
            UnitType responseData = unitTypeRepository.findOneById(unitType.getId());
            if(responseData == null){
                return ResponseHandler.generateResponse("Data is not exist in database", HttpStatus.INTERNAL_SERVER_ERROR, unitType);
            }
            unitTypeRepository.delete(responseData);

            return ResponseHandler.generateResponse("Delete Unit Type data Success", HttpStatus.OK, responseData);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }
}
