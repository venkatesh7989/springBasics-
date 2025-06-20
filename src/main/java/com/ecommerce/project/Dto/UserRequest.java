package com.ecommerce.project.Dto;

import com.ecommerce.project.Model.UserRole;
import jakarta.persistence.Entity;
import lombok.Data;

@Data
public class UserRequest {
    private String firstName;
    private String lastName;
    private String email;
    private String phone;
    private UserRole userRole;
    private UserAddressDTO address;


}
