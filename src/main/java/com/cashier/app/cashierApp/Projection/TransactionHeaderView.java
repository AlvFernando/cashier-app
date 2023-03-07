package com.cashier.app.cashierApp.Projection;

public interface TransactionHeaderView {
    String getUuid();
    Integer getPaymentMethodId();
    Integer getPayment();
    String getTransactionDate(); 
}
