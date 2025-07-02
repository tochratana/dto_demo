package com.istad.demo.repository;

import com.istad.demo.model.User;
import org.springframework.stereotype.Repository;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Repository
public class UserRepository {

    // In-memory storage using ArrayList
    private final List<User> users = new ArrayList<>();
    private final AtomicLong idGenerator = new AtomicLong(1);

    // Initialize with some sample data
    public UserRepository() {
        initializeSampleData();
    }

    private void initializeSampleData() {
        users.add(new User(1L, "John", "Doe", "john.doe@example.com", "password123"));
        users.add(new User(2L, "Jane", "Smith", "jane.smith@example.com", "password456"));
        users.add(new User(3L, "Bob", "Johnson", "bob.johnson@example.com", "password789"));

        // Set phone numbers for sample data
        users.get(0).setPhoneNumber("+1234567890");
        users.get(1).setPhoneNumber("+0987654321");
        users.get(2).setPhoneNumber("+1122334455");

        // Update ID generator to continue from last ID
        idGenerator.set(4L);
    }

    // Save user (create or update)
    public User save(User user) {
        if (user.getId() == null) {
            // Create new user
            user.setId(idGenerator.getAndIncrement());
            users.add(user);
        } else {
            // Update existing user
            Optional<User> existingUser = findById(user.getId());
            if (existingUser.isPresent()) {
                int index = users.indexOf(existingUser.get());
                users.set(index, user);
            } else {
                users.add(user);
            }
        }
        return user;
    }

    // Find user by ID
    public Optional<User> findById(Long id) {
        return users.stream()
                .filter(user -> user.getId().equals(id))
                .findFirst();
    }

    // Find all users
    public List<User> findAll() {
        return new ArrayList<>(users); // Return copy to prevent external modification
    }

    // Find user by email
    public Optional<User> findByEmail(String email) {
        return users.stream()
                .filter(user -> user.getEmail().equalsIgnoreCase(email))
                .findFirst();
    }

    // Check if user exists by ID
    public boolean existsById(Long id) {
        return users.stream()
                .anyMatch(user -> user.getId().equals(id));
    }

    // Check if user exists by email
    public boolean existsByEmail(String email) {
        return users.stream()
                .anyMatch(user -> user.getEmail().equalsIgnoreCase(email));
    }

    // Delete user by ID
    public boolean deleteById(Long id) {
        return users.removeIf(user -> user.getId().equals(id));
    }

    // Get total count
    public long count() {
        return users.size();
    }

    // Search users by name (first or last name)
    public List<User> findByNameContaining(String name) {
        return users.stream()
                .filter(user ->
                        user.getFirstName().toLowerCase().contains(name.toLowerCase()) ||
                                user.getLastName().toLowerCase().contains(name.toLowerCase()))
                .collect(Collectors.toList());
    }
}
