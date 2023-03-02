package com.cashier.app.cashierApp.Repository;

import java.util.Collection;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.cashier.app.cashierApp.Model.Item;
import com.cashier.app.cashierApp.Projection.ItemView;

public interface ItemRepository extends JpaRepository<Item,Long>{
    Item findByItemName(String itemName);

    @Query(nativeQuery = true, value = "SELECT * FROM ITEM WHERE uuid = ?1")
    Item findOneByUuid(String uuid);

    @Query(nativeQuery = true, value = "SELECT uuid, itemName, itemPrice, itemQty, unitTypeId FROM ITEM")
    Collection<ItemView> findAllItemView();
}
