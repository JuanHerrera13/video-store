package com.video.store.api.dto.customer;

import com.video.store.domain.entity.Movie;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.annotation.Id;

import java.util.List;

@Data
public class CustomerDto {

    @Id
    private String id;

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Pattern(regexp = "^(?!000|666|9\\d\\d)\\d{3}[-]?(?!00)\\d{2}[-]?(?!0000)\\d{4}$")
    private String ssn;

    @Pattern(regexp = "^\\(\\d{3}\\)[-]?\\d{3}[-]?\\d{4}$")
    private String phoneNumber;

    @NotNull
    private Integer availableMoviesCount;

    @NotNull
    private Boolean ableToRent;

    private List<Movie> rentedMovies;
}
