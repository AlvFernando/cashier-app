package com.cashier.app.cashierApp.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "item")
public class Item {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "uuid")
    private String uuid;
    @Column(name = "itemname")
    private String itemName;
    @Column(name = "itemprice")
    private Integer itemPrice;
    @Column(name = "itemqty")
    private Integer itemQty;
    @Column(name = "createdat")
    private String createdAt;
    @Column(name = "updatedat")
    private String updatedAt;
    @Column(name = "unittypeid")
    private Integer unitTypeId;
    
    public Item() {
        super();
    }

    public Item(String itemName, Integer itemPrice, Integer itemQty, String createdAt, String updatedAt,
            Integer unitTypeId, String uuid) {
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.unitTypeId = unitTypeId;
        this.uuid = uuid;
    }

    public Item(String uuid, String itemName, Integer itemPrice, Integer itemQty, Integer unitTypeId) {
        this.uuid = uuid;
        this.itemName = itemName;
        this.itemPrice = itemPrice;
        this.itemQty = itemQty;
        this.unitTypeId = unitTypeId;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUuid() {
        return uuid;
    }
    public void setUuid(String uuid) {
        this.uuid = uuid;
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
    public String getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
    public String getUpdatedAt() {
        return updatedAt;
    }
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
    public Integer getUnitTypeId() {
        return unitTypeId;
    }
    public void setUnitTypeId(Integer unitTypeId) {
        this.unitTypeId = unitTypeId;
    }
    @Override
    public String toString() {
        return "Item [id=" + id + ", uuid=" + uuid + ", itemName=" + itemName + ", itemPrice=" + itemPrice
                + ", itemQty=" + itemQty + ", createdAt=" + createdAt + ", updatedAt=" + updatedAt + ", unitTypeId="
                + unitTypeId + "]";
    }
}
