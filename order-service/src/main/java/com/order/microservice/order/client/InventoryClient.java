package com.order.microservice.order.client;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.service.annotation.GetExchange;

public interface InventoryClient {

    @GetExchange("/api/inventory/check")
    boolean isinStock(@RequestParam String skuCode, @RequestParam Integer quantity);
}
