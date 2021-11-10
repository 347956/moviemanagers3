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
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name = "name", nullable = false)
    private String name;
    @Column(name = "email", nullable = false)
    private String email;
    @Column(name = "rank", nullable = true)
    private int rank;
    @Column(name = "watched", nullable = false)
    private boolean watched;

    //method for updating the user
    public void Update(User user){
        this.name = user.name;
        this.email = user.email;
        this.rank = user.rank;
    }
}
