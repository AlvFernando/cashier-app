package com.cashier.app.cashierApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.cashier.app.cashierApp.Model.Entity.Item;
import com.cashier.app.cashierApp.Projection.ItemView;

@Repository
public interface ItemRepository extends CrudRepository<Item,Long>{
    public Item findByItemName(String itemName);

    @Query(nativeQuery = true, value = "SELECT * FROM ITEM WHERE uuid = ?1")
    public Item findOneByUuid(String uuid);

    @Query(nativeQuery = true, value = "SELECT i.uuid, i.itemName, i.itemPrice, i.itemQty, i.unitTypeId FROM ITEM i")
    public List<ItemView> getItemView();
}
