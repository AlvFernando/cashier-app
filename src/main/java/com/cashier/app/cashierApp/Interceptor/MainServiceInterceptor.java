package com.cashier.app.cashierApp.Interceptor;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.cashier.app.cashierApp.Model.Entity.ProductKey;
import com.cashier.app.cashierApp.Repository.ProductKeyRepository;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class MainServiceInterceptor implements HandlerInterceptor{
    @Autowired
    private ProductKeyRepository productKeyRepository;
    @Override
    public boolean preHandle (
        HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception{
        // ProductKey productKey = productKeyRepository.getOneById(1);
        // if(productKey.getIsActive() == false){
        //     response.sendRedirect("/product_key_activation");
        //     return false;
        // }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, 
       Object handler, ModelAndView modelAndView) throws Exception {
       
    }
    
    @Override
    public void afterCompletion
       (HttpServletRequest request, HttpServletResponse response, Object 
       handler, Exception exception) throws Exception {
       
    }
}
