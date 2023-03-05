package com.cashier.app.cashierApp.Model;

import java.util.List;

public class Transaction {
    private TransactionHeader transactionHeader;
    private List<TransactionDetail> transactionDetail;
    private Integer change;

    public Transaction(TransactionHeader transactionHeader, List<TransactionDetail> transactionDetail, Integer change) {
        this.transactionHeader = transactionHeader;
        this.transactionDetail = transactionDetail;
        this.change = change;
    }
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
    public Integer getChange() {
        return change;
    }
    public void setChange(Integer change) {
        this.change = change;
    }
    @Override
    public String toString() {
        return "Transaction [transactionHeader=" + transactionHeader + ", transactionDetail=" + transactionDetail
                + ", change=" + change + "]";
    }
}
