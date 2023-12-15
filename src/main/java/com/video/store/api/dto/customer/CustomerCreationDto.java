package com.video.store.api.dto.customer;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class CustomerCreationDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;

    @NotBlank
    @Pattern(regexp = "^(?!000|666|9\\d\\d)\\d{3}[-]?(?!00)\\d{2}[-]?(?!0000)\\d{4}$")
    private String ssn;

    @NotBlank
    @Pattern(regexp = "^\\(\\d{3}\\)[-]?\\d{3}[-]?\\d{4}$")
    private String phoneNumber;
}
