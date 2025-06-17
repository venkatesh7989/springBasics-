package com.ecommerce.project;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service

public class UserService {
    private final UserRepository userRepository;
//private long nextid = 1l;
//    private List<User> userList = new ArrayList<>();

    public UserService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    public List<User> fetchAllUsers() {

        return userRepository.findAll();
    }

    public void addUsers(User user) {
//        user.setId(nextid++);
        userRepository.save(user);

    }

    public Optional<User> fetchUser(long id) {

                return userRepository.findById(id);
//                        userList.stream().filter(user-> user.getId()==(id)).findFirst();
            }
            public boolean Updateuser(long id ,User Updateuser){
        return userRepository.findById(id)
//                userList.stream().filter(user-> user.getId()==(id)).findFirst()
                .map(exsitinguser->{
                    exsitinguser.setFirstName(Updateuser.getFirstName());
            exsitinguser.setLastName(Updateuser.getLastName());
            exsitinguser.setEmail(Updateuser.getEmail());
            exsitinguser.setPhone(Updateuser.getPhone());
            userRepository.save(exsitinguser);
           return true;
        }).orElse(false);
            }
        }




