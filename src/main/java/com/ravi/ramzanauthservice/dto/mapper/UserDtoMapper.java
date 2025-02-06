package com.ravi.ramzanauthservice.dto.mapper;

import com.ravi.ramzanauthservice.dto.UserResponseDto;
import com.ravi.ramzanauthservice.modal.User;
import org.springframework.stereotype.Service;

import java.util.function.BiFunction;

@Service
public class UserDtoMapper implements BiFunction<User, String, UserResponseDto> {

    @Override
    public UserResponseDto apply(User user, String jwtToken) {
        return new UserResponseDto(user.getId(), user.getName(), user.getEmail(), user.getPinCode(), user.getRoles(), jwtToken);
    }
}
