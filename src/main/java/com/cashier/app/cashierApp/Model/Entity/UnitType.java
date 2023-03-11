package com.cashier.app.cashierApp.Model.Entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "unittype")
public class UnitType {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "unittype")
    private String unitType;

    public UnitType() {
        super();
    }

    public UnitType(String unitType) {
        this.unitType = unitType;
    }

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public String getUnitType() {
        return unitType;
    }
    public void setUnitType(String unitType) {
        this.unitType = unitType;
    }
    @Override
    public String toString() {
        return "UnitType [id=" + id + ", unitType=" + unitType + "]";
    }
}
