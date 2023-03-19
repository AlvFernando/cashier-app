package com.cashier.app.cashierApp.Model.Entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactionheader")
public class TransactionHeader {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "transactiondate")
    private String transactionDate;
    @Column(name = "payment")
    private Integer payment;
    // @Column(name = "paymentmethodid")
    // private Integer paymentMethodId;
    @OneToOne(cascade = CascadeType.ALL)
    private PaymentMethod paymentMethod;
    @Column(name = "uuid")
    private String uuid;

    public TransactionHeader() {
        super();
    }

    // public TransactionHeader(String transactionDate, Integer payment, Integer paymentMethodId, String uuid) {
    //     this.transactionDate = transactionDate;
    //     this.payment = payment;
    //     this.paymentMethodId = paymentMethodId;
    //     this.uuid = uuid;
    // }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(String transactionDate) {
        this.transactionDate = transactionDate;
    }
    public Integer getPayment() {
        return payment;
    }
    public void setPayment(Integer payment) {
        this.payment = payment;
    }
    
    // public Integer getPaymentMethodId() {
    //     return paymentMethodId;
    // }
    // public void setPaymentMethodId(Integer paymentMethodId) {
    //     this.paymentMethodId = paymentMethodId;
    // }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }


}
