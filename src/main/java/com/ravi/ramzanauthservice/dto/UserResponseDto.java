package com.ravi.ramzanauthservice.dto;

import com.ravi.ramzanauthservice.modal.JwtToken;
import com.ravi.ramzanauthservice.modal.Role;

import java.util.Set;

public record UserResponseDto(Integer id, String name, String email, String pincode, Set<Role> roles, String jwtToken) {
}
