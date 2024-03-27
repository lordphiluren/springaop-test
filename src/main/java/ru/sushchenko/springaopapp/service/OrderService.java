package ru.sushchenko.springaopapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sushchenko.springaopapp.aspects.annotations.UpdateAnnotation;
import ru.sushchenko.springaopapp.entity.Order;
import ru.sushchenko.springaopapp.repo.OrderRepo;
import ru.sushchenko.springaopapp.utils.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class OrderService {
    private final OrderRepo orderRepo;

    public Order addOrder(Order order) {
        return orderRepo.save(order);
    }

    public void deleteOrder(Long orderId) {
        orderRepo.deleteById(orderId);
    }
    @UpdateAnnotation
    public Order updateOrder(Order order) {
        return orderRepo.save(order);
    }

    public Order getOrder(Long orderId) {
        return orderRepo.findById(orderId)
                .orElseThrow(() -> new EntityNotFoundException("Order id: " + orderId + " not found"));
    }
}
