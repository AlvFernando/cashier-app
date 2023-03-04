package com.cashier.app.cashierApp.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "transactiondetail")
public class TransactionDetail {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "transactionheaderid")
    private Integer transactionHeaderId;
    @Column(name = "itemid")
    private String itemId;
    @Column(name = "amount")
    private Integer amount;

    public TransactionDetail() {
        super();
    }

    public TransactionDetail(String itemId, Integer amount) {
        this.itemId = itemId;
        this.amount = amount;
    }
    
    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getTransactionHeaderId() {
        return transactionHeaderId;
    }
    public void setTransactionHeaderId(Integer transactionHeaderId) {
        this.transactionHeaderId = transactionHeaderId;
    }
    public String getItemId() {
        return itemId;
    }
    public void setItemId(String itemId) {
        this.itemId = itemId;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    @Override
    public String toString() {
        return "TransactionDetail [id=" + id + ", transactionHeaderId=" + transactionHeaderId + ", itemId=" + itemId
                + ", amount=" + amount + "]";
    }
}
