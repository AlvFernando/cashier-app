package com.cashier.app.cashierApp.Repository;

import org.springframework.data.repository.CrudRepository;

import com.cashier.app.cashierApp.Model.Entity.ProductKey;

public interface ProductKeyRepository extends CrudRepository<ProductKey, Long>{
    public ProductKey getOneById(Integer id);
}
