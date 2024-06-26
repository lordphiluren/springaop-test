package ru.sushchenko.springaopapp.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.sushchenko.springaopapp.entity.User;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
}
