package com.cashier.app.cashierApp.Controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cashier.app.cashierApp.Model.Entity.ProductKey;
import com.cashier.app.cashierApp.Service.ProductKeyService;

@RestController
@RequestMapping("/productkey")
public class ProductKeyController {
    @Autowired
    private ProductKeyService productKeyService;

    //get all
    @GetMapping("/getall")
    public List<ProductKey> getAll() throws ExecutionException, InterruptedException{
        return productKeyService.getProductKey();
    }

    //activation function
}
