package com.video.store.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class MovieDeleteDto {

    @NotBlank
    private String title;
}
