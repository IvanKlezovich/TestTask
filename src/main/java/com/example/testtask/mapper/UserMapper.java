package com.example.testtask.mapper;

import com.example.testtask.dto.PageResponseDto;
import com.example.testtask.dto.UserDto;
import com.example.testtask.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.springframework.data.domain.Page;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "id", source = "id")
    @Mapping(target = "name", source = "name")
    @Mapping(target = "birthday", source = "birthday")
    @Mapping(target = "phoneData", source = "phoneData")
    @Mapping(target = "emailData", source = "emailData")
    @Mapping(target = "balance", source = "account.balance")
    UserDto toDto(User user);

    default PageResponseDto<UserDto> toPageRespone(Page<User> users){
        List<UserDto> dtoList = users.stream()
            .map(this::toDto)
            .toList();
        return new PageResponseDto<>(dtoList,
            users.getTotalPages(), users.getTotalElements(),
            users.getSize(), users.getNumber());
    }
}
