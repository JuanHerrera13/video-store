package com.video.store.domain.entity;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.UUID;

@Data
@Document(collection = "customers")
public class Customer {

    @Id
    private String id = UUID.randomUUID().toString();

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @Pattern(regexp = "^(?!000|666|9\\d\\d)\\d{3}[-]?(?!00)\\d{2}[-]?(?!0000)\\d{4}$")
    private String ssn;

    @Pattern(regexp = "^\\(\\d{3}\\)[-]?\\d{3}[-]?\\d{4}$")
    private String phoneNumber;

    @NotNull
    private Integer rentedMovies;

    @NotNull
    private Boolean ableToRent;
}
