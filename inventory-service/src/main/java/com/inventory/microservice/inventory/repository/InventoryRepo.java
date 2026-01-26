package com.inventory.microservice.inventory.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.inventory.microservice.inventory.model.Inventory;

public interface InventoryRepo extends JpaRepository<Inventory,Long> {

    boolean existsBySkuCodeAndQuantityIsGreaterThanEqual(String skuCode,Integer quantity);
}
