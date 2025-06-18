package com.ecommerce.project.EcomApplication.Dto;

import com.ecommerce.project.EcomApplication.Model.UserRole;
import lombok.Data;

@Data
public class UserResponse {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole userRole;
    private UserAddressDTO address;
}
