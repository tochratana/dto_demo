package com.istad.demo.service;

import com.istad.demo.dto.CreateUserRequestDTO;
import com.istad.demo.dto.UpdateUserRequestDTO;
import com.istad.demo.dto.UserResponseDTO;
import com.istad.demo.mapper.UserMapper;
import com.istad.demo.model.User;
import com.istad.demo.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private UserMapper userMapper;

    // Create a new user
    public UserResponseDTO createUser(CreateUserRequestDTO requestDTO) {
        // Check if email already exists
        if (userRepository.existsByEmail(requestDTO.getEmail())) {
            throw new RuntimeException("Email already exists: " + requestDTO.getEmail());
        }

        // Convert DTO to Model
        User user = userMapper.toModel(requestDTO, null);

        // Save to in-memory storage
        User savedUser = userRepository.save(user);

        // Convert Model back to Response DTO
        return userMapper.toResponseDTO(savedUser);
    }

    // Get user by ID
    public Optional<UserResponseDTO> getUserById(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toResponseDTO);
    }

    // Get all users
    public List<UserResponseDTO> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Update user
    public Optional<UserResponseDTO> updateUser(Long id, UpdateUserRequestDTO requestDTO) {
        return userRepository.findById(id)
                .map(user -> {
                    // Check email uniqueness if email is being updated
                    if (requestDTO.getEmail() != null &&
                            !requestDTO.getEmail().equalsIgnoreCase(user.getEmail()) &&
                            userRepository.existsByEmail(requestDTO.getEmail())) {
                        throw new RuntimeException("Email already exists: " + requestDTO.getEmail());
                    }

                    userMapper.updateModelFromDTO(user, requestDTO);
                    User updatedUser = userRepository.save(user);
                    return userMapper.toResponseDTO(updatedUser);
                });
    }

    // Delete user
    public boolean deleteUser(Long id) {
        return userRepository.deleteById(id);
    }

    // Find user by email
    public Optional<UserResponseDTO> getUserByEmail(String email) {
        return userRepository.findByEmail(email)
                .map(userMapper::toResponseDTO);
    }

    // Search users by name
    public List<UserResponseDTO> searchUsersByName(String name) {
        return userRepository.findByNameContaining(name)
                .stream()
                .map(userMapper::toResponseDTO)
                .collect(Collectors.toList());
    }

    // Get user count
    public long getUserCount() {
        return userRepository.count();
    }
}
