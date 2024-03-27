package ru.sushchenko.springaopapp;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import ru.sushchenko.springaopapp.entity.Order;
import ru.sushchenko.springaopapp.entity.User;
import ru.sushchenko.springaopapp.entity.enums.Status;
import ru.sushchenko.springaopapp.service.OrderService;
import ru.sushchenko.springaopapp.service.UserService;

import java.util.HashSet;

@SpringBootTest
class SpringAopAppApplicationTests {
    @Autowired
    private UserService userService;
    @Autowired
    private OrderService orderService;
    @Test
    void contextLoads() {
    }

    @Test
    public void testUserServiceLogs() {
        User user1 = new User();
        user1.setName("Alice");
        user1.setEmail("alice@example.com");
        user1.setOrders(new HashSet<>());

        User user2 = new User();
        user2.setName("Bob");
        user2.setEmail("bob@example.com");
        user2.setOrders(new HashSet<>());

        User savedUser1 = userService.addUser(user1);
        User savedUser2 = userService.addUser(user2);
        try {
            userService.getUser(23132131L);
        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
        userService.getUser(savedUser1.getId());

        savedUser2.setName("UPDATED NAME");
        userService.updateUser(savedUser2);

        userService.deleteUser(savedUser1.getId());
    }
    @Test
    public void testOrderServiceLogs() {
        User user1 = new User();
        user1.setName("Alice");
        user1.setEmail("alice@example.com");
        user1.setOrders(new HashSet<>());

        User savedUser1 = userService.addUser(user1);

        Order order1 = new Order();
        order1.setDescription("First order");
        order1.setStatus(Status.CREATED);
        order1.setUser(savedUser1);

        Order order2 = new Order();
        order2.setDescription("Second order");
        order2.setStatus(Status.CREATED);
        order2.setUser(savedUser1);

        Order savedOrder1 = orderService.addOrder(order1);
        Order savedOrder2 = orderService.addOrder(order2);

        order1.setStatus(Status.DECLINED);
        orderService.updateOrder(savedOrder1);

        orderService.getOrder(savedOrder1.getId());

        orderService.deleteOrder(savedOrder2.getId());
    }
}
