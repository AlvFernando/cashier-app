package com.cashier.app.cashierApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cashier.app.cashierApp.Model.Entity.TransactionDetail;
import com.cashier.app.cashierApp.Projection.TransactionDetailView;

@Repository
public interface TransactionDetailRepository extends CrudRepository<TransactionDetail, Long>{
    public TransactionDetail findByTransactionHeaderId(Integer id);

    @Query(nativeQuery = true, value = "SELECT itemName, amount, itemPrice FROM transactiondetail td JOIN item i on td.itemid=i.id where td.transactionheaderid=?1")
    public List<TransactionDetailView> findTransactionDetailViewByTransactionHeaderId(Integer id);

    @Query(nativeQuery = true, value ="SELECT SUM(amount*itemPrice) as totalPrice FROM transactiondetail td JOIN item i on td.itemid=i.id WHERE td.transactionheaderid=?1")
    public Integer getTotalPrice(Integer id);
}
