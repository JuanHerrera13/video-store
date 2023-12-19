package com.video.store.api.controller;

import com.video.store.api.dto.customer.CustomerDto;
import com.video.store.api.dto.movie.MovieRentalDto;
import com.video.store.domain.service.RentalService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequiredArgsConstructor
public class RentalController extends RootController {

    private final RentalService rentalService;

    @PostMapping("/movies-rental")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto rentMovie(@Valid @RequestBody MovieRentalDto movieRentalDto) {
        return this.rentalService.movieRental(movieRentalDto);
    }
}
