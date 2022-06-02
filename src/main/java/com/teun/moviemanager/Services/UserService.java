package com.teun.moviemanager.Services;


import com.teun.moviemanager.DTO.UserDTO;
import com.teun.moviemanager.Models.APIUser;
import com.teun.moviemanager.Repositories.IUserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final IUserRepository repository;
    private final BCryptPasswordEncoder encoder;

    public List<APIUser> GetAllUsers(){

        return repository.findAll();
    }
    public APIUser getApiUserByUserName(String username){
        return repository.findByUserName(username).orElseThrow(EntityNotFoundException:: new);
    }
    public UserDTO GetUserByUserName(String userName){
        //.orElseThrow(EntityNotFoundException::new)
        return createDTOFromUserModel(repository.findByUserName(userName).orElseThrow(EntityNotFoundException::new));
    }

    //Will check if there already is a user present in the database with this name
    public Boolean checkIfUserNameExists(String name){
        return repository.existsByName(name);
    }
    //Will check if there already is a user present in the database with this Email
    public Boolean checkIfUserEmailExists(String email){
        return repository.existsByEmail(email);
    }

    public UserDTO getUserByEmail(String email){
        return createDTOFromUserModel(repository.findByEmail(email).orElseThrow(EntityNotFoundException::new));
    }

    public UserDTO GetUser(Long Id){
        APIUser apiUser = repository.findById(Id).orElse(null);
        Optional<APIUser> user = Optional.ofNullable(apiUser);
        if(user.isPresent()){
            UserDTO userDTO = createDTOFromUserModel(apiUser);
            return userDTO;
        }
        else{
            throw new EntityNotFoundException();
        }
    }

    public UserDTO CreateUser(UserDTO userDTO){
        APIUser toCreateAPIUser = new APIUser();
        Optional<APIUser> byUsername = repository.findByUserName(userDTO.getUserName());
        if(byUsername.isPresent()){
            throw new RuntimeException("User with username " + userDTO.getUserName() + " already exists");
        }
        if(userDTO.getRole() == null){
            userDTO.setRole("USER");
        }
        userDTO.setPassword(encoder.encode(userDTO.getPassword()));
        toCreateAPIUser.updateWithDTO(userDTO);
        //save the user to the DB and return a DTO from the saved user
        return createDTOFromUserModel(repository.save(toCreateAPIUser));
    }

    public void DeleteUser(Long Id){

        repository.deleteById(Id);
    }
    public boolean UpdateUser(UserDTO updUserDTO){
        UserDTO userDTO = updUserDTO;
        APIUser APIUser = repository.findById(updUserDTO.getId()).orElse(null);
        if(APIUser != null){
            APIUser.updateWithDTO(userDTO);
            APIUser.setPassword(encoder.encode(APIUser.getPassword()));
            repository.save(APIUser);
            return true;
        }
        else {
            return false;
        }
    }

    //private methods
    //create a DTO from a model to return to the controller
    private UserDTO createDTOFromUserModel(APIUser APIUser){
        UserDTO userDTO = new UserDTO();
        userDTO.setId(APIUser.getId());
        userDTO.setName(APIUser.getName());
        userDTO.setUserName(APIUser.getUserName());
        userDTO.setEmail(APIUser.getEmail());
        userDTO.setRole(APIUser.getRole());
        return(userDTO);
    }
}
