package com.teun.moviemanager.Controller;


import com.teun.moviemanager.Models.User;
import com.teun.moviemanager.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    UserService service;

    @GetMapping
    public ResponseEntity<List<User>> GetAllUsers(){
        List<User> users = null;
        users = service.GetAllUsers();
        if( users != null){
            return ResponseEntity.ok().body(users);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<User> getUserPath(@PathVariable(value = "id")Long id){
        User user = service.GetUser(id);
        if( user != null){
            return ResponseEntity.ok().body(user);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }

}
