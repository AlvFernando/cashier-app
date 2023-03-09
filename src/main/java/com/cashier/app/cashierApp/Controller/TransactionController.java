package com.cashier.app.cashierApp.Controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import com.cashier.app.cashierApp.Helper.DateHelper;
import com.cashier.app.cashierApp.Model.ResponseHandler;
import com.cashier.app.cashierApp.Model.Entity.Item;
import com.cashier.app.cashierApp.Model.Entity.Transaction;
import com.cashier.app.cashierApp.Model.Entity.TransactionDetail;
import com.cashier.app.cashierApp.Model.Entity.TransactionHeader;
import com.cashier.app.cashierApp.Model.Request.TransactionRequest;
import com.cashier.app.cashierApp.Projection.TransactionDetailView;
import com.cashier.app.cashierApp.Model.View.TransactionView;
import com.cashier.app.cashierApp.Repository.ItemRepository;
import com.cashier.app.cashierApp.Repository.PaymentMethodRepository;
import com.cashier.app.cashierApp.Repository.TransactionDetailRepository;
import com.cashier.app.cashierApp.Repository.TransactionHeaderRepository;
import com.itextpdf.html2pdf.ConverterProperties;
import com.itextpdf.html2pdf.HtmlConverter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.transaction.Transactional;

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

    @Autowired
    DateHelper dateHelper;

    @Autowired
    ServletContext servletContext;
    private final TemplateEngine templateEngine;

    public TransactionController(TemplateEngine templateEngine) {
        this.templateEngine = templateEngine;
    }

    @Transactional
    @PostMapping("/transaction")
    public ResponseEntity<Object> addTransaction(@RequestBody Transaction transaction){
        try {
            //init variable data
            Integer payment = transaction.getTransactionHeader().getPayment();
            Integer paymentMethodId = transaction.getTransactionHeader().getPaymentMethodId();
            Integer totalPrice = 0;
            Integer change = 0;
            List<Item> itemList = new ArrayList<>();
            LocalDateTime localDateTime = LocalDateTime.now();
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
                totalPrice = totalPrice+(tempItem.getItemPrice()*transaction.getTransactionDetail().get(i).getAmount());
                itemList.add(tempItem);
            }

            //validation payment and totalPrice
            if(totalPrice>payment){
                return ResponseHandler.generateResponse("Payment is less than the total price", HttpStatus.BAD_REQUEST, transaction);
            }

            change = payment - totalPrice;

            //generate uuid
            TransactionHeader transactionHeaderCheckByUuid;
            UUID uuid;
            String uuidAsString;
            do {
                uuid = UUID.randomUUID();
                uuidAsString = uuid.toString();
                transactionHeaderCheckByUuid = transactionHeaderRepository.findOneByUuid(uuidAsString);
            } while (transactionHeaderCheckByUuid != null);

            //add transaction header
            TransactionHeader transactionHeader = new TransactionHeader(
                localDateTime.toString(),
                payment,
                paymentMethodId,
                uuidAsString
            );
            TransactionHeader transactionHeaderResponse = transactionHeader;
            transactionHeaderRepository.save(transactionHeader);

            //add transaction detail & updating stock
            for(int i=0;i<itemList.size();i++){
                transactionDetailList.add(
                    new TransactionDetail(
                        transactionHeader.getId(),
                        itemList.get(i).getId().toString(),
                        transaction.getTransactionDetail().get(i).getAmount()
                    )
                );
                //update item stock
                itemList.get(i).setItemQty(itemList.get(i).getItemQty()-transaction.getTransactionDetail().get(i).getAmount());
                itemRepository.save(itemList.get(i));
            }
            transactionDetailRepository.saveAll(transactionDetailList);

            Transaction responseData = new Transaction(
                transactionHeaderResponse,
                transactionDetailList,
                change
            );

            return ResponseHandler.generateResponse("Success", HttpStatus.OK, responseData);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse("Error", HttpStatus.MULTI_STATUS, null);
        }
    }

    @PostMapping("/gettransaction")
    public ResponseEntity<Object> getAllTransaction(@RequestBody TransactionRequest transactionRequest){
        try {
            //validation input
            if(dateHelper.isValidDate(transactionRequest.getStartDate()) == false || dateHelper.isValidDate(transactionRequest.getEndDate()) == false){
                return ResponseHandler.generateResponse("Request Date Data is Invalid. Date format is yyyy-MM-dd", HttpStatus.BAD_REQUEST, transactionRequest);
            }

            List<TransactionView> responseData = new ArrayList<>();
            List<TransactionHeader> headerData = transactionHeaderRepository.findByDate(transactionRequest.getStartDate(), transactionRequest.getEndDate());

            //create transaction view data
            Integer totalPrice = 0;
            Integer change = 0;
            for(int i=0;i<headerData.size();i++){
                List<TransactionDetailView> detailData = transactionDetailRepository.findTransactionDetailViewByTransactionHeaderId(headerData.get(i).getId());
                totalPrice = transactionDetailRepository.getTotalPrice(headerData.get(i).getId());
                change = headerData.get(i).getPayment()-totalPrice;
                responseData.add(
                    new TransactionView(
                        new TransactionHeader(
                            headerData.get(i).getTransactionDate(),
                            headerData.get(i).getPayment(),
                            headerData.get(i).getPaymentMethodId(),
                            headerData.get(i).getUuid()
                        ), 
                        detailData,
                        totalPrice,
                        change)
                );
            }
            return ResponseHandler.generateResponse("Success", HttpStatus.OK, responseData);
        } catch (Exception e) {
            // TODO: handle exception
            return ResponseHandler.generateResponse("Error", HttpStatus.MULTI_STATUS, null);
        }
    }

    // @GetMapping("/invoice")
    // public ResponseEntity<?> download2(HttpServletRequest request, HttpServletResponse response) throws IOException{
    //     Context context = new Context();
    //     String orderHtml = templateEngine.process("invoiceTemplate", context);

    //     ByteArrayOutputStream target = new ByteArrayOutputStream();
    //     ConverterProperties converterProperties = new ConverterProperties();
    //     converterProperties.setBaseUri("http://localhost:8080");
    //     /* Call convert method */
    //     HtmlConverter.convertToPdf(orderHtml, target, converterProperties);
    //     byte[] bytes = target.toByteArray();

    //     return ResponseEntity.ok()
    //     .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=order.pdf")
    //     .contentType(MediaType.APPLICATION_PDF)
    //     .body(bytes);
    // }
    @GetMapping("/invoice")
    String InvoiceDownload(){
        return "invoiceTemplate";
    }
}