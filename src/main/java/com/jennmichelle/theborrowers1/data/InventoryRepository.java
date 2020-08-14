package com.jennmichelle.theborrowers1.data;

import com.jennmichelle.theborrowers1.models.inventoryItem;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InventoryRepository extends CrudRepository<inventoryItem, Integer> {
}