package com.cashier.app.cashierApp.Model.View;

import java.util.List;

import com.cashier.app.cashierApp.Model.Entity.TransactionHeader;
import com.cashier.app.cashierApp.Projection.TransactionDetailView;

public class TransactionView {
    private TransactionHeader transactionHeader;
    private List<TransactionDetailView> transactionDetail;
    
    public TransactionView() {
        super();
    }

    public TransactionView(TransactionHeader transactionHeader, List<TransactionDetailView> transactionDetail) {
        this.transactionHeader = transactionHeader;
        this.transactionDetail = transactionDetail;
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
    @Override
    public String toString() {
        return "TransactionView [transactionHeader=" + transactionHeader + ", transactionDetail=" + transactionDetail
                + "]";
    }
}
