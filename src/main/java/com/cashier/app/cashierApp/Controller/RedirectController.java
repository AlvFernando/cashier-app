package com.cashier.app.cashierApp.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {
    @GetMapping("/product_key_activation")
    public String redirect(){
        return "activationPage";
    }
}
