package com.cashier.app.cashierApp.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cashier.app.cashierApp.Model.ResponseHandler;
import com.cashier.app.cashierApp.Model.Transaction;

@Controller
@RequestMapping("/api")
public class TransactionController {
    @PostMapping("/transaction")
    public ResponseEntity<Object> addTransaction(@RequestBody Transaction transaction){
        try {
            System.out.println(transaction);
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, transaction);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse("Error", HttpStatus.MULTI_STATUS, null);
        }
    }
}
