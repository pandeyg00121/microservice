package com.order.microservice.order.service;

import com.order.microservice.order.client.InventoryClient;
import com.order.microservice.order.dto.OrderRequest;
import com.order.microservice.order.dto.OrderResponse;
import com.order.microservice.order.model.Order;
import com.order.microservice.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderService {

    private final OrderRepository orderRepository;
    private final InventoryClient inventoryClient;

    public void placeOrder(OrderRequest orderRequest){

        var isProductInStock = inventoryClient.isinStock(orderRequest.skuCode(),orderRequest.quantity());

        if(isProductInStock){
            // map OrderRequest (DTO) ---> Order (Entity)
            Order order = new Order();
            order.setOrderNumber(UUID.randomUUID().toString());
            order.setPrice(orderRequest.price());
            order.setSkuCode(orderRequest.skuCode());
            order.setQuantity(orderRequest.quantity());

            //Save Product to ProductRepository
            orderRepository.save(order);

            log.info("Order {} is saved", order.getId());
        }else{
            throw new RuntimeException("Product with skuCode " + orderRequest.skuCode() + " is not in stock");
        }
    }

    public List<OrderResponse> getAllOrders() {
        //Find all the orders (Entity)
        List<Order> orders = orderRepository.findAll();

        //Convert Entity -> DTO
        return orders.stream()
                .map(order -> new OrderResponse(
                        order.getId(),
                        order.getOrderNumber(),
                        order.getSkuCode(),
                        order.getPrice(),
                        order.getQuantity()
                ))
                .toList();
    }
}
