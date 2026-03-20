package com.mora.backendangkorliving.service;

import com.mora.backendangkorliving.DTOs.request.ProfileUpdateRequest;
import com.mora.backendangkorliving.DTOs.request.UserUpdateRequest;
import com.mora.backendangkorliving.DTOs.response.UserRespone;
import com.mora.backendangkorliving.model.User;
import org.hibernate.sql.Update;

import java.util.List;

public interface UserService {
    List<UserRespone> getAllUsers();
    User getUserById(Long id);
    User updateUser(Long id, UserUpdateRequest request);
    void deleteUser(Long id);
    List<UserRespone> getAllTenants();
    User getProfile(String email);
    User updateProfile(String email, UserUpdateRequest request);
    // 👉 Update profile only
//    User updateProfile(UserUpdateRequest request);
//    User getProfile(Long id);
    // 👉 Reset password only
    User resetPassword(Long id);
}

