package com.teun.moviemanager.Models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name="member")
@NoArgsConstructor
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name", nullable = false)
    private String name;
    @Column(name="email", nullable = true)
    private String email;
    @Column(name="rank", nullable = true)
    private int rank;
    @Column(name="password", nullable = false)
    private String password;
}
