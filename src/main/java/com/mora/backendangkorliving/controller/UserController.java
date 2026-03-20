package com.mora.backendangkorliving.controller;

import com.mora.backendangkorliving.DTOs.request.ProfileUpdateRequest;
import com.mora.backendangkorliving.DTOs.request.UserUpdateRequest;
import com.mora.backendangkorliving.DTOs.response.UserRespone;
import com.mora.backendangkorliving.model.User;
import com.mora.backendangkorliving.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // ADMIN endpoints
    @GetMapping
    public ResponseEntity<List<UserRespone>> getAllUsers() {
        return ResponseEntity.ok(userService.getAllUsers());
    }
    @GetMapping("/tenants")
    public ResponseEntity<List<UserRespone>> getAllTenants() {
        return ResponseEntity.ok(userService.getAllTenants());
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

//    @PutMapping("/{id}")
//    public User updateUser(@PathVariable Long id, @RequestBody UserUpdateRequest request) {
//        return userService.updateUser(id, request);
//    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
    }

    // TENANT/USER endpoints
    @GetMapping("/profile")
    public User getProfile(@AuthenticationPrincipal UserDetails userDetails) {
        return userService.getProfile(userDetails.getUsername());
    }

    @PutMapping("/profile")
    public User updateProfile(@AuthenticationPrincipal UserDetails userDetails,
                              @RequestBody  UserUpdateRequest request) {
        return userService.updateProfile(userDetails.getUsername(), request);
    }
//    @PutMapping("/profile")
//    public ResponseEntity<User> updateProfile(@PathVariable Long id,
//                                              @RequestBody UserUpdateRequest request) {
//        User updatedUser = userService.updateProfile(id, request);
//        return ResponseEntity.ok(updatedUser);
//    }
    @PutMapping("/{id}/reset-password")
    public ResponseEntity<String> resetPassword(@PathVariable Long id) {
        userService.resetPassword(id);
        return ResponseEntity.ok("Password reset successfully to default: AngkorLiving@123");
    }
}

