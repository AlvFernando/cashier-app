package com.cashier.app.cashierApp.Model.Request;

public class ItemRequest {
    private String UUID;
    private String itemName;
    private Integer itemPrice;
    private Integer itemQty;
    private Integer unitTypeId;

    public String getUUID() {
        return UUID;
    }
    public void setUUID(String UUID) {
        this.UUID = UUID;
    }
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
        return "ItemRequest [UUID=" + UUID + ", itemName=" + itemName + ", itemPrice=" + itemPrice + ", itemQty="
                + itemQty + ", unitTypeId=" + unitTypeId + "]";
    }
}
