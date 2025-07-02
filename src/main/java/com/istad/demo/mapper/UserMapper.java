package com.istad.demo.mapper;

import com.istad.demo.dto.CreateUserRequestDTO;
import org.springframework.stereotype.Component;
import com.istad.demo.model.User;
import com.istad.demo.dto.UpdateUserRequestDTO;
import com.istad.demo.dto.UserResponseDTO;

@Component
public class UserMapper {

    // Convert CreateUserRequestDTO to User Model
    public User toModel(CreateUserRequestDTO dto, Long id) {
        if (dto == null) {
            return null;
        }

        User user = new User();
        user.setId(id);
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword()); // Should be encrypted in real app
        user.setPhoneNumber(dto.getPhoneNumber());
        user.setCreatedAt(java.time.LocalDateTime.now());
        user.setUpdatedAt(java.time.LocalDateTime.now());

        return user;
    }

    // Convert User Model to UserResponseDTO
    public UserResponseDTO toResponseDTO(User user) {
        if (user == null) {
            return null;
        }

        return new UserResponseDTO(
                user.getId(),
                user.getFirstName(),
                user.getLastName(),
                user.getEmail(),
                user.getPhoneNumber(),
                user.getCreatedAt(),
                user.getUpdatedAt()
        );
    }

    // Update User Model from UpdateUserRequestDTO
    public void updateModelFromDTO(User user, UpdateUserRequestDTO dto) {
        if (user == null || dto == null) {
            return;
        }

        if (dto.getFirstName() != null && !dto.getFirstName().trim().isEmpty()) {
            user.setFirstName(dto.getFirstName());
        }
        if (dto.getLastName() != null && !dto.getLastName().trim().isEmpty()) {
            user.setLastName(dto.getLastName());
        }
        if (dto.getEmail() != null && !dto.getEmail().trim().isEmpty()) {
            user.setEmail(dto.getEmail());
        }
        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().trim().isEmpty()) {
            user.setPhoneNumber(dto.getPhoneNumber());
        }

        user.setUpdatedAt(java.time.LocalDateTime.now());
    }
}
