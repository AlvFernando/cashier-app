package com.cashier.app.cashierApp.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "paymentmethod")
public class PaymentMethod {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "paymentmethod")
    private String paymentMethod;

    public PaymentMethod() {
        super();
    }

    public PaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    @Override
    public String toString() {
        return "PaymentMethod [id=" + id + ", paymentMethod=" + paymentMethod + "]";
    }
}
