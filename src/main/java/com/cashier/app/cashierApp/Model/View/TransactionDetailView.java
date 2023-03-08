package com.cashier.app.cashierApp.Model.View;

public class TransactionDetailView {
    String itemName;
    Integer amount;
    Integer itemPrice;
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public Integer getAmount() {
        return amount;
    }
    public void setAmount(Integer amount) {
        this.amount = amount;
    }
    public Integer getItemPrice() {
        return itemPrice;
    }
    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }
    @Override
    public String toString() {
        return "TransactionDetailView [itemName=" + itemName + ", amount=" + amount + ", itemPrice=" + itemPrice + "]";
    }
}
