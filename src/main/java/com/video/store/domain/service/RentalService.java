package com.video.store.domain.service;

import com.video.store.api.dto.customer.CustomerDto;
import com.video.store.api.dto.movie.MovieRentalDto;
import com.video.store.api.mapping.CustomerMapper;
import com.video.store.domain.entity.Customer;
import com.video.store.domain.entity.Movie;
import com.video.store.exception.CustomerException;
import com.video.store.exception.MovieException;
import com.video.store.infrastructure.repository.CustomerRepository;
import com.video.store.infrastructure.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

import static com.video.store.domain.enumerator.Error.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class RentalService {

    private final MovieService movieService;

    private final CustomerService customerService;

    private final CustomerRepository customerRepository;

    private final MovieRepository movieRepository;

    private final CustomerMapper customerMapper;

    /**
     * Movies Rental logic
     *
     * @param movieRentalDto movie rental data transfer object
     * @return a customer data transfer object
     */
    public CustomerDto movieRental(MovieRentalDto movieRentalDto) {
        final Customer customer = this.customerService.findCustomerById(movieRentalDto.getCustomerId());
        final List<Movie> movieList = this.movieService.findMoviesById(movieRentalDto.getMoviesIds());
        if (!customer.getAbleToRent()) {
            throw new CustomerException(CUSTOMER_CANNOT_RENT.getErrorDescription());
        }
        for (Movie movie : movieList) {
            if (movie.getNumberOfCopies() < 1) {
                throw new MovieException(MOVIE_CANNOT_BE_RENTED.getErrorDescription());
            } else if (customer.getRentedMovies().contains(movie)) {
                log.error("Customer has already rented the film with id " + movie.getId());
                throw new CustomerException(CUSTOMER_HAS_ALREADY_RENTED_THE_FILM.getErrorDescription());
            }
            customer.getRentedMovies().add(movie);
            customer.setAvailableMoviesCount(customer.getAvailableMoviesCount() - 1);
            if (customer.getRentedMovies().size() >= 5) {
                customer.setAbleToRent(false);
            }
            movie.setNumberOfCopies(movie.getNumberOfCopies() - 1);
            customerRepository.save(customer);
            movieRepository.save(movie);
        }
        return this.customerMapper.customerToCustomerDto(customer);
    }
}
