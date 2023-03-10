package com.cashier.app.cashierApp.Repository;

import org.springframework.data.repository.CrudRepository;

import com.cashier.app.cashierApp.Model.Entity.PaymentMethod;

public interface PaymentMethodRepository extends CrudRepository<PaymentMethod,Integer>{
    public PaymentMethod findByPaymentMethod(String paymentMethodName);
    public PaymentMethod findOneById(Integer paymentMethodId);
}
