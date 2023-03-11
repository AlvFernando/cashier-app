package com.cashier.app.cashierApp.Repository;

import org.springframework.data.repository.CrudRepository;

import com.cashier.app.cashierApp.Model.Entity.UnitType;

public interface UnitTypeRepository extends CrudRepository<UnitType,Long>{
    public UnitType findOneByUnitType(String unitTypeName);
    public UnitType findOneById(Integer unitTypeId);
}
