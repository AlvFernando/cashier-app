package com.cashier.app.cashierApp.Repository;

import org.springframework.data.repository.CrudRepository;

import com.cashier.app.cashierApp.Model.PaymentMethod;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod,Integer>{
    
}
