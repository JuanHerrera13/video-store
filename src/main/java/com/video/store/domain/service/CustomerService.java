package com.video.store.domain.service;

import com.video.store.api.dto.customer.CustomerCreationDto;
import com.video.store.api.dto.customer.CustomerDeleteDto;
import com.video.store.api.dto.customer.CustomerDto;
import com.video.store.api.dto.customer.CustomerUpdateDto;
import com.video.store.api.mapping.CustomerMapper;
import com.video.store.domain.entity.Customer;
import com.video.store.exception.CustomerException;
import com.video.store.exception.NotFoundException;
import com.video.store.infrastructure.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.video.store.domain.enumerator.Error.*;
import static com.video.store.domain.service.utils.ServiceUtils.updateIfNonNull;

@Slf4j
@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    private final CustomerMapper customerMapper;

    /**
     * Find a customer by id
     *
     * @param id customer id
     * @return a customer
     */
    public Customer findCustomerById(String id) {
        return this.customerRepository.findById(id).orElseThrow(() ->
                new NotFoundException(CUSTOMER_NOT_FOUND.getErrorDescription()));
    }

    /**
     * Fetch all customers available in the db
     *
     * @return a customer data transfer object list
     */
    public List<CustomerDto> fetchCustomersList() {
        final List<Customer> customers = this.customerRepository.findAll();
        if (customers.isEmpty()) {
            log.error("There are no customers in db");
            throw new NotFoundException(NO_CUSTOMERS_FOUND.getErrorDescription());
        }
        log.info("Fetching all customers in db");
        return this.customerMapper.customerListToCustomerListDto(customers);
    }

    /**
     * Find a customer by first and last name
     *
     * @param firstName customer's first name
     * @param lastName  customer's last name
     * @return a customer data transfer object
     */
    public CustomerDto findCustomerByFirstAndLastName(String firstName, String lastName) {
        final Customer customer = this.customerRepository
                .findByFirstNameIgnoreCaseAndLastNameIgnoreCase(firstName, lastName)
                .orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND.getErrorDescription()));
        log.info("Retrieving customer with first name '" + firstName + "' and last name '" + lastName + "'");
        return this.customerMapper.customerToCustomerDto(customer);
    }

    /**
     * Adds a customer in db
     *
     * @param customerCreationDto a customer creation data transfer object
     * @return an added customer data transfer object
     * @throws CustomerException when is given a ssn that already exists
     */
    public CustomerDto addCustomer(CustomerCreationDto customerCreationDto) {
        final Optional<Customer> validatingCustomer = this.customerRepository.findBySsn(customerCreationDto.getSsn());
        if (validatingCustomer.isPresent()) {
            log.error("Customer " + customerCreationDto.getFirstName() + " " + customerCreationDto.getLastName() +
                    " already exists in db");
            throw new CustomerException(CUSTOMER_ALREADY_EXISTS.getErrorDescription());
        }
        final Customer customer = this.customerMapper.customerCreationDtoToCustomer(customerCreationDto);
        customer.setAbleToRent(Boolean.TRUE);
        customer.setAvailableMoviesCount(5);
        customer.setRentedMovies(new ArrayList<>());
        log.info("Adding customer to db");
        this.customerRepository.save(customer);
        log.info("Customer added in db");
        return this.customerMapper.customerToCustomerDto(customer);
    }

    /**
     * Updates a customer in db
     *
     * @param customerUpdateDto customer update data transfer object
     * @return a data transfer object of the updated customer
     */
    public CustomerDto updateCustomer(CustomerUpdateDto customerUpdateDto) {
        final Customer updatedCustomer = this.customerRepository.findById(customerUpdateDto.getId())
                .orElseThrow(() -> new NotFoundException(CUSTOMER_NOT_FOUND.getErrorDescription()));
        updateIfNonNull(customerUpdateDto.getFirstName(), updatedCustomer::setFirstName);
        updateIfNonNull(customerUpdateDto.getLastName(), updatedCustomer::setLastName);
        updateIfNonNull(customerUpdateDto.getPhoneNumber(), updatedCustomer::setPhoneNumber);
        updateIfNonNull(customerUpdateDto.getAvailableMoviesCount(), updatedCustomer::setAvailableMoviesCount);
        updateIfNonNull(customerUpdateDto.getAbleToRent(), updatedCustomer::setAbleToRent);
        updateIfNonNull(customerUpdateDto.getRentedMovies(), updatedCustomer::setRentedMovies);
        log.info("Updating customer in db");
        this.customerRepository.save(updatedCustomer);
        log.info("Customer updated");
        return this.customerMapper.customerToCustomerDto(updatedCustomer);
    }

    /**
     * Deletes a customer by first and last name
     *
     * @param customerDeleteDto customer delete data transfer object
     */
    public void deleteCustomer(CustomerDeleteDto customerDeleteDto) {
        final CustomerDto customerDto = findCustomerByFirstAndLastName(customerDeleteDto.getFirstName(),
                customerDeleteDto.getLastName());
        log.info("Deleting customer in db");
        this.customerRepository.delete(this.customerMapper.customerDtoToCustomer(customerDto));
        log.info("Customer deleted");
    }
}
