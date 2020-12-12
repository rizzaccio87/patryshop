package com.projects.patryshop.services;

import com.projects.patryshop.dto.OrderDTO;
import com.projects.patryshop.entities.Cake;
import com.projects.patryshop.entities.Order;
import com.projects.patryshop.exceptions.EntityNotFoundException;
import com.projects.patryshop.repos.CakeRepository;
import com.projects.patryshop.repos.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository repository;

    @Autowired
    private CakeRepository cakeRepository;

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        Cake cake = cakeRepository.findById(orderDTO.getCakeId()).get();
        Order order = new Order();
        order.setCake(cake);
        order.setCreationTimestamp(
                Objects.isNull(orderDTO.getCreationTimestamp()) ? ZonedDateTime.now() : orderDTO.getCreationTimestamp());
        order.setAmount(orderDTO.getAmount());
        Order savedOrder = repository.save(order);
        return this.convertToOrderDTO(savedOrder);
    }

    @Override
    public List<OrderDTO> readAll() {
        return repository.findAll()
                .stream()
                .map(this::convertToOrderDTO)
                .collect(Collectors.toList());
    }

    @Override
    public OrderDTO read(Long id) throws EntityNotFoundException {
        return repository.findById(id)
                .map(this::convertToOrderDTO)
                .orElseThrow(() -> new EntityNotFoundException("Order not found for this id :: " + id));
    }

    @Override
    public OrderDTO update(Long id, OrderDTO orderDTO) throws EntityNotFoundException {
        Order retrievedOrder = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found for this id :: " + id));
        retrievedOrder.setAmount(orderDTO.getAmount());
        Order updatedOrder = repository.save(retrievedOrder);
        return this.convertToOrderDTO(updatedOrder);
    }

    @Override
    public boolean delete(Long id) throws EntityNotFoundException {
        Order retrievedOrder = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found for this id :: " + id));
        repository.delete(retrievedOrder);
        return true;
    }

    private OrderDTO convertToOrderDTO(Order order) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setOrderId(order.getId());
        orderDTO.setCakeId(order.getCake().getId());
        orderDTO.setCakeName(order.getCake().getName());
        orderDTO.setAmount(order.getAmount());
        orderDTO.setCreationTimestamp(order.getCreationTimestamp());
        orderDTO.setPrice(order.getCake().getPrice());
        return orderDTO;
    }

}
