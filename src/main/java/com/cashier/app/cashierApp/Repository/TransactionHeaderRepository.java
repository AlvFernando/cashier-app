package com.cashier.app.cashierApp.Repository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cashier.app.cashierApp.Model.TransactionHeader;

@Repository
public interface TransactionHeaderRepository extends CrudRepository<TransactionHeader,Long>{
    @Query(nativeQuery = true, value = "SELECT * FROM transactionheader WHERE uuid = ?1")
    public TransactionHeader findOneByUuid(String uuid);
}
