package com.video.store.api.dto.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerUpdateDto {

    @NotBlank
    private String id;

    private String firstName;

    private String lastName;

    private String phoneNumber;

    private Integer rentedMovies;

    private Boolean ableToRent;
}
