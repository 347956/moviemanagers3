package com.teun.moviemanager.Models;

import com.teun.moviemanager.DTO.UserDTO;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name="user")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class APIUser {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "username", nullable = false)
    private String userName;
    @Column(name = "email", nullable = true)
    private String email;
    @Column(name = "password", nullable = true)
    private String password;
//    @ManyToMany(fetch = FetchType.LAZY)
//    @JoinTable(	name = "user_roles",
//            joinColumns = @JoinColumn(name = "user_id"),
//            inverseJoinColumns = @JoinColumn(name = "role_id"))
//    private Collection<Role> roles = new HashSet<>();
    @Column(name= "role", nullable = true)
    private String role;

    //method for updating the user
    public void UpdateApiUser(APIUser APIUser){
        this.name = APIUser.name;
        this.userName = APIUser.userName;
        this.email = APIUser.email;
        this.role = APIUser.role;
    }
    public void updateWithDTO(UserDTO userDTO){
        this.id = userDTO.getId();
        this.name = userDTO.getName();
        this.userName = userDTO.getUserName();
        this.email = userDTO.getEmail();
        this.password = userDTO.getPassword();
        this.role = userDTO.getRole();
    }
}
