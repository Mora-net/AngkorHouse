package com.mora.backendangkorliving.service;

import com.mora.backendangkorliving.DTOs.request.ProfileUpdateRequest;
import com.mora.backendangkorliving.DTOs.request.UserUpdateRequest;
import com.mora.backendangkorliving.DTOs.response.UserRespone;
import com.mora.backendangkorliving.model.Role;
import com.mora.backendangkorliving.model.User;
import com.mora.backendangkorliving.Repository.UserRepository;
import com.mora.backendangkorliving.service.UserService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public List<UserRespone> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(u -> new UserRespone(
                        u.getId(),
                        u.getUsername(),
                        u.getEmail(),
                        u.getRole().name(),
                        u.getPhone(),
                        u.getProfileImage()
                ))
                .toList();
    }

    @Override
    public User getUserById(Long id) {
        return userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));
    }

    @Override
    public User updateUser(Long id, UserUpdateRequest request) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        // Validation
        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            user.setUsername(request.getUsername());
        }
        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }
        if (request.getProfileImage() != null) {
            user.setProfileImage(request.getProfileImage());
        }
        if (request.getRole() != null) {
            try {
                user.setRole(Role.valueOf(request.getRole().toUpperCase()));
            } catch (IllegalArgumentException e) {
                throw new RuntimeException("Invalid role: " + request.getRole());
            }
        }

        return userRepository.save(user);
    }

    @Override
    public void deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new EntityNotFoundException("User not found with id: " + id);
        }
        userRepository.deleteById(id);
    }

    @Override
    public User getProfile(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));
    }

    @Override
    public User updateProfile(String email, UserUpdateRequest request) {
        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new EntityNotFoundException("User not found with email: " + email));

        // Role-check: only TENANT or USER can update their own profile
        if (!(user.getRole() == Role.TENANT )) {
            throw new AccessDeniedException("Only TENANT or USER can update profile");
        }

        // Validation
        if (request.getUsername() != null && !request.getUsername().isBlank()) {
            user.setUsername(request.getUsername());
        }
        if (request.getPhone() != null) {
            user.setPhone(request.getPhone());
        }
        if (request.getProfileImage() != null) {
            user.setProfileImage(request.getProfileImage());
        }

        return userRepository.save(user);
    }
    @Override
    public List<UserRespone> getAllTenants() {
        return userRepository.findByRole(Role.TENANT)
                .stream()
                .map(u -> new UserRespone(
                        u.getId(),
                        u.getUsername(),
                        u.getEmail(),
                        u.getRole().name(),
                        u.getPhone(),
                        u.getProfileImage()
                ))
                .toList();
    }

    @Override
//    public User updateProfile( UserUpdateRequest request) {
//
//
//        if (request.getUsername() != null && !request.getUsername().isBlank()) {
//            user.setUsername(request.getUsername());
//        }
//        if (request.getPhone() != null) {
//            user.setPhone(request.getPhone());
//        }
//        if (request.getProfileImage() != null) {
//            user.setProfileImage(request.getProfileImage());
//        }
//        if (request.getRole() != null) {
//            try {
//                user.setRole(Role.valueOf(request.getRole().toUpperCase()));
//            } catch (IllegalArgumentException e) {
//                throw new RuntimeException("Invalid role: " + request.getRole());
//            }
//        }
//
//        return userRepository.save(user);
//    }

//    @Override
    public User resetPassword(Long id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + id));

        // ✅ Set default password
        user.setPassword(passwordEncoder.encode("AngkorLiving@123"));

        return userRepository.save(user);
    }
}


