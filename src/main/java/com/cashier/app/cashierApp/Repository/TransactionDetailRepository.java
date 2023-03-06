package com.cashier.app.cashierApp.Repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cashier.app.cashierApp.Model.TransactionDetail;

@Repository
public interface TransactionDetailRepository extends CrudRepository<TransactionDetail, Long>{
    
}
