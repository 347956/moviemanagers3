package com.teun.moviemanager.Services;


import com.teun.moviemanager.Models.User;
import com.teun.moviemanager.Repositories.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    IUserRepository repository;

    public List<User> GetAllUsers(){

        return repository.findAll();
    }

    public User GetUser(Long Id){

        return repository.findById(Id).orElse(null);
    }
    public User CreateUser(User user){

        return repository.save(user);
    }
    public void DeleteUser(Long Id){

        repository.deleteById(Id);
    }
    public boolean UpdateUser(User updUser){

        User user = repository.findById(updUser.getId()).orElse(null);
        if(user != null){
            user.Update(updUser);
            repository.save(user);
            return true;
        }
        else {
            return false;
        }
    }
}
