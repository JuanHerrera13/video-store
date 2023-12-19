package com.video.store.api.dto.movie;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MovieRentalDto {

    @NotBlank
    private String customerId;

    @NotBlank
    private String movieId;
}
