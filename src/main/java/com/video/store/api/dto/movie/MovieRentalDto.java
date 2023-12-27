package com.video.store.api.dto.movie;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;

@Data
public class MovieRentalDto {

    @NotBlank
    private String customerId;

    @NotNull
    private List<String> moviesIds;
}
