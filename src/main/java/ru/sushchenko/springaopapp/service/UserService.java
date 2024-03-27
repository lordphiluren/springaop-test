package ru.sushchenko.springaopapp.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.sushchenko.springaopapp.aspects.annotations.UpdateAnnotation;
import ru.sushchenko.springaopapp.entity.User;
import ru.sushchenko.springaopapp.repo.UserRepo;
import ru.sushchenko.springaopapp.utils.EntityNotFoundException;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepo userRepo;
    public User addUser(User user) {
        return userRepo.save(user);
    }

    public void deleteUser(Long userId) {
        userRepo.deleteById(userId);
    }
    @UpdateAnnotation
    public User updateUser(User user) {
        return userRepo.save(user);
    }

    public User getUser(Long userId) {
        return userRepo.findById(userId)
                .orElseThrow(() -> new EntityNotFoundException("User id: " + userId + " not found"));
    }
}
