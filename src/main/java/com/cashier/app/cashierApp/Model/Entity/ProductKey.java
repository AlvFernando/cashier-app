package com.cashier.app.cashierApp.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "productkey")
public class ProductKey {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Column(name = "isactive")
    private Boolean isActive;
    @Column(name = "productkey")
    private String productKey;

    public ProductKey(){
        super();
    }
    
    public ProductKey(Boolean isActive, String productKey) {
        this.isActive = isActive;
        this.productKey = productKey;
    }
    
    public Boolean getIsActive() {
        return isActive;
    }
    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }
    public String getProductKey() {
        return productKey;
    }
    public void setProductKey(String productKey) {
        this.productKey = productKey;
    }
    @Override
    public String toString() {
        return "ProductKey [isActive=" + isActive + ", productKey=" + productKey + "]";
    }
}
