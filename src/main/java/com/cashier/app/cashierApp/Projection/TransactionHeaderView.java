package com.cashier.app.cashierApp.Projection;

public interface TransactionHeaderView {
    String getUuid();
    String getPaymentMethod();
    Integer getPayment();
    String getTransactionDate(); 
}
