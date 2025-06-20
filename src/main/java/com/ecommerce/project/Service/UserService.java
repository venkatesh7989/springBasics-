package com.ecommerce.project.Service;

import com.ecommerce.project.Dto.UserAddressDTO;
import com.ecommerce.project.Dto.UserRequest;
import com.ecommerce.project.Dto.UserResponse;
import com.ecommerce.project.Model.User;
import com.ecommerce.project.Model.UserAddress;
import com.ecommerce.project.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public List<UserResponse> fetchAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(this::mapToUserResponse)
                .collect(Collectors.toList());
    }

    public void addUsers(UserRequest userRequest) {
        User user = new User();
          updateUserfromRequest(user,userRequest);
        userRepository.save(user);
    }



    public Optional<UserResponse> fetchUser(long id) {
        return userRepository.findById(id).map(this::mapToUserResponse);
    }

    public boolean updateUser(long id, UserRequest updateUserRequest) {
        return userRepository.findById(id).map(existingUser -> {
            updateUserfromRequest(existingUser , updateUserRequest) ;
            userRepository.save(existingUser);
            return true;
        }).orElse(false);
    }

        private void updateUserfromRequest(User user, UserRequest userRequest) {
            user.setFirstName(userRequest.getFirstName());
            user.setLastName(userRequest.getLastName());
            user.setPhone(userRequest.getPhone());
            user.setEmail(userRequest.getEmail());

            if (userRequest.getAddress() != null) {
                UserAddress address = new UserAddress();
                address.setStreet(userRequest.getAddress().getStreet());
                address.setCity(userRequest.getAddress().getCity());
                address.setState(userRequest.getAddress().getState());
                address.setZipcode(userRequest.getAddress().getZipcode());
                address.setCountry(userRequest.getAddress().getCountry());
                user.setUserAddress(address);
            }


        }

        private UserResponse mapToUserResponse(User user) {
        UserResponse response = new UserResponse();
        response.setId(String.valueOf(user.getId()));
        response.setFirstName(user.getFirstName());
        response.setLastName(user.getLastName());
        response.setPhone(user.getPhone());
        response.setEmail(user.getEmail());
        response.setUserRole(user.getUserRole());

        if (user.getUserAddress() != null) {
            UserAddressDTO addressDTO = new UserAddressDTO();
            addressDTO.setStreet(user.getUserAddress().getStreet());
            addressDTO.setCity(user.getUserAddress().getCity());
            addressDTO.setState(user.getUserAddress().getState());
            addressDTO.setCountry(user.getUserAddress().getCountry());
            addressDTO.setZipcode(user.getUserAddress().getZipcode());
            response.setAddress(addressDTO);
        }

        return response;
    }
}
