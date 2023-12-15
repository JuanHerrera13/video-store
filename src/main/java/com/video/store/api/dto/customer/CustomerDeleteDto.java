package com.video.store.api.dto.customer;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CustomerDeleteDto {

    @NotBlank
    private String firstName;

    @NotBlank
    private String lastName;
}
