package com.cashier.app.cashierApp.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cashier.app.cashierApp.Model.ResponseHandler;
import com.cashier.app.cashierApp.Model.Entity.PaymentMethod;
import com.cashier.app.cashierApp.Model.Request.PaymentMethodRequest;
import com.cashier.app.cashierApp.Repository.PaymentMethodRepository;

@Controller
@RequestMapping("/api")
public class PaymentMethodController {
    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @CrossOrigin
    @PostMapping("/paymentmethod")
    public ResponseEntity<Object> addPaymentMethod(@RequestBody PaymentMethodRequest paymentMethod){
        try {
            //validation input
            if(paymentMethod.getPaymentMethodName() == null){
                return ResponseHandler.generateResponse("Failed add data, request payment method name is invalid", HttpStatus.BAD_REQUEST, paymentMethod);
            }
            if(paymentMethod.getPaymentMethodName().isEmpty() || paymentMethod.getPaymentMethodName().trim().isEmpty()){
                return ResponseHandler.generateResponse("Failed add data, request payment method name is invalid", HttpStatus.BAD_REQUEST, paymentMethod);
            }
            //validation name
            PaymentMethod existingData = paymentMethodRepository.findByPaymentMethod(paymentMethod.getPaymentMethodName());
            if(existingData != null){
                return ResponseHandler.generateResponse("PaymentMethod data already exist in database", HttpStatus.INTERNAL_SERVER_ERROR, paymentMethod);
            }
            PaymentMethod responseData = new PaymentMethod(
                paymentMethod.getPaymentMethodName()
            );

            responseData = paymentMethodRepository.save(responseData);
            return ResponseHandler.generateResponse("Success add payment method", HttpStatus.CREATED, responseData);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
    }

    @CrossOrigin
    @DeleteMapping("/paymentmethod")
    public ResponseEntity<Object> deletePaymentMethod(@RequestBody PaymentMethodRequest paymentMethod){
        try {
            //validation input
            if(paymentMethod.getId() == null){
                return ResponseHandler.generateResponse("Failed delete data, request id is invalid", HttpStatus.BAD_REQUEST, paymentMethod);
            }

            PaymentMethod responseData = paymentMethodRepository.findOneById(paymentMethod.getId());
            if(responseData == null){
                return ResponseHandler.generateResponse("Failed delete data, payment method doesn't exist in database", HttpStatus.INTERNAL_SERVER_ERROR, paymentMethod);
            }
            paymentMethodRepository.delete(responseData);
            return ResponseHandler.generateResponse("Success delete payment method", HttpStatus.OK, responseData);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse(e.getMessage(), HttpStatus.MULTI_STATUS, null);
        }
        
    }
}
