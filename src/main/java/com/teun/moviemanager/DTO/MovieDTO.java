package com.teun.moviemanager.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MovieDTO {
    private Long id;
    private int movieId;
    private String name;
    private String genre;
    private String genre2;
    private boolean watched;
}
