package com.istad.demo.mapper;

import com.istad.demo.dto.CreateUserRequestDTO;
import com.istad.demo.dto.UpdateUserRequestDTO;
import com.istad.demo.dto.UserResponseDTO;
import com.istad.demo.model.User;
import org.mapstruct.*;

@Mapper(componentModel = "spring")
public interface UserMapper {

    // Create User from DTO
    @Mapping(target = "id", source = "id") // Optional: if you want to pass custom id
    @Mapping(target = "createdAt", expression = "java(java.time.LocalDateTime.now())")
    @Mapping(target = "updatedAt", expression = "java(java.time.LocalDateTime.now())")
    User toModel(CreateUserRequestDTO dto, Long id);

    // Convert entity to response DTO
    UserResponseDTO toResponseDTO(User user);

    // Update existing User from DTO
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void updateModelFromDTO(UpdateUserRequestDTO dto, @MappingTarget User user);
}
