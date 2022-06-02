package com.teun.moviemanager.DTO;

import lombok.Data;

@Data
public class UserDTO {

    private Long id;
    private String name;
    private String userName;
    private String email;
    private String password;
//    private EnumRole roles;
    private String role;

}
