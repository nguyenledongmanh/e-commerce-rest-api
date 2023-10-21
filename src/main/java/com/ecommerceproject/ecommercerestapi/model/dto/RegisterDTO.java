package com.ecommerceproject.ecommercerestapi.model.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RegisterDTO {
    private String name;

    @NotEmpty(message = "username should not be empty")
    @Size(min = 6, message = "username has at least 6 characters")
    private String username;

    @Email
    @NotEmpty(message = "email should not be empty")
    private String email;

    @NotEmpty(message = "password should not be empty")
    @Size(min = 8, message = "password has at least 8 characters")
    private String password;
}