package ru.sushchenko.springaopapp.aspects;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;
import ru.sushchenko.springaopapp.entity.Order;
import ru.sushchenko.springaopapp.entity.User;

import java.util.Arrays;
import java.util.stream.Collectors;

@Aspect
@Component
@Slf4j
public class LoggingAspect {
    @Pointcut("execution(public * ru.sushchenko.springaopapp.service.*.*(..))")
    public void callAtService() { }

    @Pointcut("@annotation(ru.sushchenko.springaopapp.aspects.annotations.UpdateAnnotation) && (args(user,..))")
    public void updateCallAtUserService(User user) { }
    @Pointcut("@annotation(ru.sushchenko.springaopapp.aspects.annotations.UpdateAnnotation) && (args(order,..))")
    public void updateCallAtOrderService(Order order) { }

    @Before("callAtService()")
    public void beforeCallAtUserService(JoinPoint joinPoint) {
        String args = Arrays.stream(joinPoint.getArgs())
                .map(Object::toString)
                .collect(Collectors.joining(","));
        log.info("Before {}, args=[{}]", joinPoint, args);
    }
    @AfterReturning(pointcut = "callAtService()", returning = "user")
    public void afterReturnCallAtUserService(JoinPoint joinPoint, User user) {
        log.info("After {}, returns=[{}]", joinPoint, user);
    }
    @AfterReturning(pointcut = "callAtService()", returning = "order")
    public void afterReturnCallAtOrderService(JoinPoint joinPoint, Order order) {
        log.info("After {}, returns=[{}]", joinPoint, order);
    }
    @AfterThrowing(pointcut = "callAtService()", throwing = "e")
    public void afterThrowingCallAtUserService(JoinPoint joinPoint, RuntimeException e) {
        log.error("{} threw exception=[{}] with message=[{}]", joinPoint, e.toString(), e.getMessage());
    }
    @AfterReturning(value = "execution(* ru.sushchenko.springaopapp.service.UserService.deleteUser(..)) && args(userId)")
    public void afterUserDeleted(Long userId) {
        log.info("User with id: {} deleted", userId);
    }
    @AfterReturning(value = "execution(* ru.sushchenko.springaopapp.service.OrderService.deleteOrder(..)) && args(orderId)")
    public void afterOrderDeleted(Long orderId) {
        log.info("Order with id: {} deleted", orderId);
    }
    @AfterReturning(value = "updateCallAtUserService(user)", returning = "retUser")
    public void afterReturnUserUpdate(User user, User retUser) {
        log.info("User with id: {}, updated: {}", user.getId(), retUser);
    }
    @AfterReturning(value = "updateCallAtOrderService(order)", returning = "retOrder")
    public void afterReturnOrderUpdate(Order order, Order retOrder) {
        log.info("Order with id: {}, updated: {}", order.getId(), retOrder);
    }
}
