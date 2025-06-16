package com.ecommerce.project;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("api/Users")
public  class UserController {
@Autowired
    private  UserService userService;


    @GetMapping
   // @RequestMapping(value ="/api/getuserlist" ,method = RequestMethod.GET )
    public ResponseEntity <List<User>>getallusers(){
        return new ResponseEntity<>(userService.fetchAllUsers(), HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable long id ){
//        User user = userService.fetchUser(id);
//        if (user == null)
//        return ResponseEntity.notFound().build();
//        return ResponseEntity.ok(user);
        return userService.fetchUser(id).map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> addUsers(@PathVariable long id,
                                           @RequestBody User Updateuser){
       boolean Updated = userService.Updateuser(id,Updateuser);
       if(Updated)
      return ResponseEntity.ok("user updated successfuly");
       return ResponseEntity.notFound().build();

    }
    @PostMapping
    public ResponseEntity<String> creatusers(@RequestBody User user){
        userService.addUsers(user);
        return ResponseEntity.ok("user add scessfuly");
    }
}
