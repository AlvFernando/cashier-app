package com.cashier.app.cashierApp.Projection;

public interface ItemView {
    String getUuid();
    String getItemName();
    Integer getItemPrice();
    Integer getItemQty();
    Integer getUnitTypeId();
}
