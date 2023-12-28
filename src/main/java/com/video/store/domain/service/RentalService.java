package com.video.store.domain.service;

import com.video.store.api.dto.customer.CustomerDto;
import com.video.store.api.dto.movie.MovieRentalDto;
import com.video.store.api.dto.movie.MovieReturnDto;
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
     * @throws CustomerException when customer is not able to rent or already rented the given movie
     * @throws MovieException    when there are no more movie's copies available
     */
    public CustomerDto movieRental(MovieRentalDto movieRentalDto) {
        final Customer customer = this.customerService.findCustomerById(movieRentalDto.getCustomerId());
        log.info("Customer with id " + movieRentalDto.getCustomerId() + " found");
        final List<Movie> movieList = this.movieService.findMoviesById(movieRentalDto.getMoviesIds());
        if (!customer.getAbleToRent()) {
            log.error("Customer is not able to rent");
            throw new CustomerException(CUSTOMER_CANNOT_RENT.getErrorDescription());
        }
        for (Movie movie : movieList) {
            if (movie.getNumberOfCopies() < 1) {
                log.error("Movie can't be rented. There are no more copies");
                throw new MovieException(MOVIE_CANNOT_BE_RENTED.getErrorDescription());
            } else if (customer.getRentedMovies().contains(movie)) {
                log.error("Customer has already rented the film with id " + movie.getId());
                throw new CustomerException(CUSTOMER_HAS_ALREADY_RENTED_THIS_FILM.getErrorDescription());
            }
            customer.getRentedMovies().add(movie);
            log.info(movie.getTitle() + " added to customer's rented movies");
            customer.setAvailableMoviesCount(customer.getAvailableMoviesCount() - 1);
            if (customer.getRentedMovies().size() >= 5) {
                customer.setAbleToRent(false);
            }
            movie.setNumberOfCopies(movie.getNumberOfCopies() - 1);
            customerRepository.save(customer);
            movieRepository.save(movie);
        }
        log.info("Movie rental finished");
        return this.customerMapper.customerToCustomerDto(customer);
    }

    /**
     * Method responsible for a movie return logic
     *
     * @param movieReturnDto movie return data transfer object
     * @return a customer data transfer object
     * @throws MovieException when is passed a movie that wasn't rented by the customer
     */
    public CustomerDto movieReturn(MovieReturnDto movieReturnDto) {
        final Customer customer = this.customerService.findCustomerById(movieReturnDto.getCustomerId());
        final List<Movie> movieList = this.movieService.findMoviesById(movieReturnDto.getMoviesIds());
        for (Movie movie : movieList) {
            if (!customer.getRentedMovies().contains(movie)) {
                log.error("Customer didn't rent the movie with id " + movie.getId());
                throw new MovieException(CUSTOMER_DID_NOT_RENT_THIS_MOVIE.getErrorDescription());
            }
            customer.getRentedMovies().remove(movie);
            log.info(movie.getTitle() + " removed from customer's rented movies");
            customer.setAvailableMoviesCount(customer.getAvailableMoviesCount() + 1);
            if (customer.getRentedMovies().size() < 5) {
                customer.setAbleToRent(true);
            }
            movie.setNumberOfCopies(movie.getNumberOfCopies() + 1);
            customerRepository.save(customer);
            movieRepository.save(movie);
        }
        log.info("Movie return finished");
        return this.customerMapper.customerToCustomerDto(customer);
    }
}
