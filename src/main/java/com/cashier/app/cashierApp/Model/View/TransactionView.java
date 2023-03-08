package com.cashier.app.cashierApp.Model.View;

import java.util.List;

import com.cashier.app.cashierApp.Model.Entity.TransactionHeader;
import com.cashier.app.cashierApp.Projection.TransactionDetailView;

public class TransactionView {
    private TransactionHeader transactionHeader;
    private List<TransactionDetailView> transactionDetail;
    private Integer totalPrice;
    private Integer change;
    
    public TransactionView() {
        super();
    }
    
    public TransactionView(TransactionHeader transactionHeader, List<TransactionDetailView> transactionDetail) {
        this.transactionHeader = transactionHeader;
        this.transactionDetail = transactionDetail;
    }

    public TransactionView(TransactionHeader transactionHeader, List<TransactionDetailView> transactionDetail,
            Integer totalPrice, Integer change) {
        this.transactionHeader = transactionHeader;
        this.transactionDetail = transactionDetail;
        this.totalPrice = totalPrice;
        this.change = change;
    }

    public TransactionHeader getTransactionHeader() {
        return transactionHeader;
    }
    public void setTransactionHeader(TransactionHeader transactionHeader) {
        this.transactionHeader = transactionHeader;
    }
    public List<TransactionDetailView> getTransactionDetail() {
        return transactionDetail;
    }
    public void setTransactionDetail(List<TransactionDetailView> transactionDetail) {
        this.transactionDetail = transactionDetail;
    }
    public Integer getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Integer totalPrice) {
        this.totalPrice = totalPrice;
    }

    public Integer getChange() {
        return change;
    }

    public void setChange(Integer change) {
        this.change = change;
    }

    @Override
    public String toString() {
        return "TransactionView [transactionHeader=" + transactionHeader + ", transactionDetail=" + transactionDetail
                + "]";
    }
}
