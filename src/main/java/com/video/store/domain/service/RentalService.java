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

import static com.video.store.domain.enumerator.Error.CUSTOMER_CANNOT_RENT;
import static com.video.store.domain.enumerator.Error.MOVIE_CANNOT_BE_RENTED;

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
        final Movie movie = this.movieService.findMovieById(movieRentalDto.getMovieId());
        if (!customer.getAbleToRent()) {
            throw new CustomerException(CUSTOMER_CANNOT_RENT.getErrorDescription());
        } else if (movie.getNumberOfCopies() < 1) {
            throw new MovieException(MOVIE_CANNOT_BE_RENTED.getErrorDescription());
        }
        customer.getRentedMovies().add(movie);
        customer.setAvailableMoviesCount(customer.getAvailableMoviesCount() - 1);
        if (customer.getRentedMovies().size() >= 5) {
            customer.setAbleToRent(false);
        }
        movie.setNumberOfCopies(movie.getNumberOfCopies() - 1);
        customerRepository.save(customer);
        movieRepository.save(movie);
        return this.customerMapper.customerToCustomerDto(customer);
    }
}
