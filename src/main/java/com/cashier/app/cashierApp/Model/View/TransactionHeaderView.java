package com.cashier.app.cashierApp.Model.View;

public class TransactionHeaderView {
    private Integer id;
    private String transactionDate;
    private Integer payment;
    private String paymentMethod;
    private String uuid;
    
    public TransactionHeaderView(Integer id, String transactionDate, Integer payment, String paymentMethod,
            String uuid) {
        this.id = id;
        this.transactionDate = transactionDate;
        this.payment = payment;
        this.paymentMethod = paymentMethod;
        this.uuid = uuid;
    }
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
    public String getPaymentMethod() {
        return paymentMethod;
    }
    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
    }
    
}
