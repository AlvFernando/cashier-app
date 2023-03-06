package com.cashier.app.cashierApp.Controller;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cashier.app.cashierApp.Model.Item;
import com.cashier.app.cashierApp.Model.ResponseHandler;
import com.cashier.app.cashierApp.Model.Transaction;
import com.cashier.app.cashierApp.Model.TransactionDetail;
import com.cashier.app.cashierApp.Model.TransactionHeader;
import com.cashier.app.cashierApp.Repository.ItemRepository;
import com.cashier.app.cashierApp.Repository.PaymentMethodRepository;
import com.cashier.app.cashierApp.Repository.TransactionDetailRepository;
import com.cashier.app.cashierApp.Repository.TransactionHeaderRepository;

@Controller
@RequestMapping("/api")
public class TransactionController {
    @Autowired
    ItemRepository itemRepository;

    @Autowired
    PaymentMethodRepository paymentMethodRepository;

    @Autowired
    TransactionHeaderRepository transactionHeaderRepository;

    @Autowired
    TransactionDetailRepository transactionDetailRepository;

    @PostMapping("/transaction")
    public ResponseEntity<Object> addTransaction(@RequestBody Transaction transaction){
        try {
            System.out.println(transaction.getTransactionDetail().size());
            //init variable data
            Integer payment = transaction.getTransactionHeader().getPayment();
            Integer paymentMethodId = transaction.getTransactionHeader().getPaymentMethodId();
            Integer totalPrice = 0;
            Integer change = 0;
            List<Item> itemList = new ArrayList<>();
            Instant instant = Instant.now();
            List<TransactionDetail> transactionDetailList = new ArrayList<>();
            
            //validation transaction header
            if(payment == null || paymentMethodId == null){
                return ResponseHandler.generateResponse("Transaction Header Data is Invalid", HttpStatus.BAD_REQUEST, transaction);
            }
            if(paymentMethodRepository.findById(paymentMethodId) == null){
                return ResponseHandler.generateResponse("Transaction Header Payment Method ID Data is Invalid", HttpStatus.BAD_REQUEST, transaction);
            }

            //validation transaction detail
            for(int i=0;i<transaction.getTransactionDetail().size();i++){
                Item tempItem = itemRepository.findOneByUuid(transaction.getTransactionDetail().get(i).getItemId());
                if(tempItem == null){
                    return ResponseHandler.generateResponse("Transaction Detail Data at index "+ i +" is Invalid", HttpStatus.BAD_REQUEST, transaction);
                }
                if(transaction.getTransactionDetail().get(i).getAmount() > tempItem.getItemQty()){
                    return ResponseHandler.generateResponse("Transaction Detail Data Quantity at index "+i+" is more than the stock quantity", HttpStatus.BAD_REQUEST, transaction);
                }
                totalPrice = totalPrice+tempItem.getItemPrice();
                itemList.add(tempItem);
            }
            System.out.println(totalPrice);
            //validation payment and totalPrice
            if(totalPrice>payment){
                return ResponseHandler.generateResponse("Payment is less than the total price", HttpStatus.BAD_REQUEST, transaction);
            }

            change = payment - totalPrice;
            System.out.println(change);
            //generate uuid
            TransactionHeader transactionHeaderCheckByUuid;
            UUID uuid;
            String uuidAsString;
            do {
                uuid = UUID.randomUUID();
                uuidAsString = uuid.toString();
                transactionHeaderCheckByUuid = transactionHeaderRepository.findOneByUuid(uuidAsString);
            } while (transactionHeaderCheckByUuid != null);
            System.out.println(itemList.size());
            //add transaction header
            TransactionHeader transactionHeader = new TransactionHeader(
                instant.toString(),
                payment,
                paymentMethodId,
                uuidAsString
            );
            transactionHeader = transactionHeaderRepository.save(transactionHeader);

            for(int i=0;i<itemList.size();i++){
                transactionDetailList.add(
                    new TransactionDetail(
                        transactionHeader.getId(),
                        itemList.get(i).getId().toString(),
                        transaction.getTransactionDetail().get(i).getAmount()
                    )
                );
            }
            transactionDetailRepository.saveAll(transactionDetailList);

            Transaction responseData = new Transaction(
                transactionHeader,
                transactionDetailList,
                change
            );

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, responseData);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse("Error", HttpStatus.MULTI_STATUS, null);
        }
    }
}
