package com.inventory.microservice.inventory.controller;

import com.inventory.microservice.inventory.dto.InventoryRequest;
import com.inventory.microservice.inventory.dto.InventoryResponse;
import com.inventory.microservice.inventory.model.Inventory;
import com.inventory.microservice.inventory.service.InventoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inventory")
@RequiredArgsConstructor
public class InventoryController {

    private final InventoryService inventoryService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public String createInventory(@RequestBody InventoryRequest inventoryRequest) {
        inventoryService.insertInventory(inventoryRequest);
        return "Inventory created Successfully...";
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    public List<InventoryResponse> getAllInventory() {
        return inventoryService.getAllInventory();
    }

    @GetMapping("/check")
    public boolean isInStock(@RequestParam String skuCode,
                             @RequestParam Integer quantity) {
        return inventoryService.isInStock(skuCode, quantity);
    }
}
