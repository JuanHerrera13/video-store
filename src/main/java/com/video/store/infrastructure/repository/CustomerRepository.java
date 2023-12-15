package com.video.store.infrastructure.repository;

import com.video.store.domain.entity.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {

    Optional<Customer> findBySsn(String ssn);

    Optional<Customer> findByFirstNameIgnoreCaseAndLastNameIgnoreCase(String firstName, String lastName);
}
