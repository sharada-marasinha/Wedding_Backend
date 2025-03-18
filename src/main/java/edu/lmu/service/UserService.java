package edu.lmu.service;

import edu.lmu.entity.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
     List<User> getAllUsers();
     Optional<User> getUserById(Integer id);
     User createUser(User user);
     User updateUser(Integer id, User updatedUser);
     void deleteUser(Integer id);
     Optional<User> findByUsername(String username);
     Optional<User> findByEmail(String email);
     boolean existsByUsername(String username);
     boolean existsByEmail(String email);
}
