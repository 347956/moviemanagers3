package com.teun.moviemanager.Controller;


import com.teun.moviemanager.Models.APIUser;
import com.teun.moviemanager.DTO.UserDTO;
import com.teun.moviemanager.Services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService service;

    @GetMapping("/all")
    public ResponseEntity<List<APIUser>> GetAllUsers(){
        List<APIUser> APIUsers;
        APIUsers = service.GetAllUsers();
        if( APIUsers != null){
            return ResponseEntity.ok().body(APIUsers);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<UserDTO> getUserPath(@PathVariable(value = "id")Long id){
        UserDTO userDTO = service.GetUser(id);
        if( userDTO != null){
            return ResponseEntity.ok().body(userDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/name/{name}")
    public ResponseEntity<UserDTO> getUserByUserNamePath(@PathVariable(value = "name")String name){
        UserDTO userDTO = service.GetUserByUserName(name);
        if( userDTO != null){
            return ResponseEntity.ok().body(userDTO);
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity deleteUser(@PathVariable(value = "id")Long id){
        service.DeleteUser(id);
        return ResponseEntity.ok().build();
    }
    @PutMapping("/update")
    public ResponseEntity updateUser(@RequestBody UserDTO userDTO){
        if(service.UpdateUser(userDTO)){
            return ResponseEntity.ok().build();
        }
        else{
            return ResponseEntity.notFound().build();
        }
    }
}
