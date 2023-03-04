package com.cashier.app.cashierApp.Model;

import java.util.List;

public class Transaction {
    private TransactionHeader transactionHeader;
    private List<TransactionDetail> transactionDetail;
    
    public TransactionHeader getTransactionHeader() {
        return transactionHeader;
    }
    public void setTransactionHeader(TransactionHeader transactionHeader) {
        this.transactionHeader = transactionHeader;
    }
    public List<TransactionDetail> getTransactionDetail() {
        return transactionDetail;
    }
    public void setTransactionDetail(List<TransactionDetail> transactionDetail) {
        this.transactionDetail = transactionDetail;
    }
    @Override
    public String toString() {
        return "Transaction [transactionHeader=" + transactionHeader + ", transactionDetail=" + transactionDetail + "]";
    }
}
