package com.mora.backendangkorliving.DTOs.request;


import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UserUpdateRequest {
    // 👉 Fields for profile update
    private String username;
    private String phone;
    private String profileImage;

    // 👉 Field for role update (optional)
    private String role; // ADMIN, TENANT, USER

    // 👉 Field for reset password
    private String password;
}

