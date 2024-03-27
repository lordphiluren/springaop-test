package ru.sushchenko.springaopapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sushchenko.springaopapp.entity.Order;
@Repository
public interface OrderRepo extends JpaRepository<Order, Long> {
}
