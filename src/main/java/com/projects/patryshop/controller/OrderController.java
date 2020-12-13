package com.projects.patryshop.controller;

import com.projects.patryshop.dto.OrderDTO;
import com.projects.patryshop.exceptions.EntityNotFoundException;
import com.projects.patryshop.exceptions.ResourceNotFoundException;
import com.projects.patryshop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api")
public class OrderController {

    @Autowired
    private OrderService service;

    @GetMapping("/orders")
    public List<OrderDTO> getAllOrders() {
        return service.readAll();
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> getOrderById(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        OrderDTO orderDTO = null;
        try {
            orderDTO = service.read(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Order not found for this id :: " + id);
        }
        return ResponseEntity.ok(orderDTO);
    }

    @PostMapping("/orders")
    public OrderDTO createOrder(@RequestBody OrderDTO orderDTO) {
        return service.create(orderDTO);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<OrderDTO> updateOrder(@PathVariable(value = "id") Long id, @RequestBody OrderDTO orderDTO) throws ResourceNotFoundException {
        OrderDTO updatedOrderDTO = null;
        try {
            updatedOrderDTO = service.update(id, orderDTO);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Order not found for this id :: " + id);
        }
        return ResponseEntity.ok(updatedOrderDTO);
    }

    @DeleteMapping("/orders/{id}")
    public Map<String, Boolean> deleteOrder(@PathVariable(value = "id") Long id) throws ResourceNotFoundException {
        Boolean deleted;
        try {
            deleted = service.delete(id);
        } catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("Order not found for this id :: " + id);
        }

        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", deleted);
        return response;
    }

}
