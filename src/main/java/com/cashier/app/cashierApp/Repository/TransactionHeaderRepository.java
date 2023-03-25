package com.cashier.app.cashierApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cashier.app.cashierApp.Model.Entity.TransactionHeader;
import com.cashier.app.cashierApp.Projection.TransactionHeaderView;

@Repository
public interface TransactionHeaderRepository extends CrudRepository<TransactionHeader,Long>, JpaSpecificationExecutor<TransactionHeader>{
    @Query(nativeQuery = true, value = "SELECT * FROM transactionheader WHERE uuid = ?1")
    public TransactionHeader findOneByUuid(String uuid);

    @Query(
        nativeQuery = true, 
        value = "SELECT * FROM transactionheader WHERE transactiondate>=?1 AND transactiondate<=?2")
    public List<TransactionHeader> findByDate(String startDate, String endDate);

    @Query(
        nativeQuery = true, 
        value = 
            "SELECT TRANSACTIONDATE, PAYMENT, PAYMENTMETHOD, UUID "+
            "FROM transactionheader th JOIN paymentmethod pm on th.paymentmethodid=pm.id " +
            "WHERE transactiondate>=?1 AND transactiondate<=?2")
    public List<TransactionHeaderView> findByDateJoined(String startDate, String endDate);
}
