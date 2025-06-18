package com.ecommerce.project.EcomApplication.Dto;

import lombok.Data;

@Data
public class UserAddressDTO {
  //  private Long id;
    private String street;
    private String  city;
    private String state;
    private String country;
    private String zipcode;
}
