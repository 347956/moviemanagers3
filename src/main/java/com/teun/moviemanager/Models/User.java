package com.teun.moviemanager.Models;

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
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = true)
    private String email;
    @Column(name = "rank", nullable = true)
    private int rank;
    @Column(name = "password", nullable = true)
    private String password;

    //method for updating the user
    public void Update(User user){
        this.name = user.name;
        this.email = user.email;
        this.rank = user.rank;
    }
}
