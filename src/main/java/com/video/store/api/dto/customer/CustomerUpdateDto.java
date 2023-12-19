package com.video.store.api.dto.customer;

import com.video.store.domain.entity.Movie;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

import java.util.List;

@Data
public class CustomerUpdateDto {

    @NotBlank
    private String id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Integer availableMoviesCount;

    private Boolean ableToRent;

    private List<Movie> rentedMovies;
}
