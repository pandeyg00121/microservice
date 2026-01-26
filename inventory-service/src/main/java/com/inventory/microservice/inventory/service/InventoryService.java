package com.inventory.microservice.inventory.service;

import com.inventory.microservice.inventory.dto.InventoryRequest;
import com.inventory.microservice.inventory.dto.InventoryResponse;
import com.inventory.microservice.inventory.model.Inventory;
import com.inventory.microservice.inventory.repository.InventoryRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class InventoryService {

    private final InventoryRepo inventoryRepo;

    public void insertInventory(InventoryRequest inventoryRequest) {
        Inventory inventory =new Inventory();
        inventory.setSkuCode(inventoryRequest.skuCode());
        inventory.setQuantity(inventoryRequest.quantity());

        inventoryRepo.save(inventory);

        log.info("Inventory {} is saved", inventory.getId());
    }

    public List<InventoryResponse> getAllInventory() {
        List<Inventory> inventoryList = inventoryRepo.findAll();

        return inventoryList.stream()
                .map(inventory -> new InventoryResponse(
                        inventory.getId(),
                        inventory.getSkuCode(),
                        inventory.getQuantity()
                ))
                .toList();
    }

    public boolean isInStock(String skuCode,Integer quantity){
        return inventoryRepo.existsBySkuCodeAndQuantityIsGreaterThanEqual(skuCode,quantity);
    }
}
