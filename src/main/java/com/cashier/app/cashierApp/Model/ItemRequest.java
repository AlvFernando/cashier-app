package com.cashier.app.cashierApp.Model;

public class ItemRequest {
    private String itemName;
    private Integer itemPrice;
    private Integer itemQty;
    private Integer unitTypeId;
    public String getItemName() {
        return itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public Integer getItemPrice() {
        return itemPrice;
    }
    public void setItemPrice(Integer itemPrice) {
        this.itemPrice = itemPrice;
    }
    public Integer getItemQty() {
        return itemQty;
    }
    public void setItemQty(Integer itemQty) {
        this.itemQty = itemQty;
    }
    public Integer getUnitTypeId() {
        return unitTypeId;
    }
    public void setUnitTypeId(Integer unitTypeId) {
        this.unitTypeId = unitTypeId;
    }
    @Override
    public String toString() {
        return "ItemRequest [itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemQty=" + itemQty
                + ", unitTypeId=" + unitTypeId + "]";
    }
}
