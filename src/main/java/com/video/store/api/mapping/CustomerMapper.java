package com.video.store.api.mapping;

import com.video.store.api.dto.customer.CustomerCreationDto;
import com.video.store.api.dto.customer.CustomerDto;
import com.video.store.domain.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper
public interface CustomerMapper {

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "ssn", target = "ssn")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "availableMoviesCount", target = "availableMoviesCount")
    @Mapping(source = "ableToRent", target = "ableToRent")
    Customer customerDtoToCustomer(CustomerDto customerDto);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "ssn", target = "ssn")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    @Mapping(source = "availableMoviesCount", target = "availableMoviesCount")
    @Mapping(source = "ableToRent", target = "ableToRent")
    CustomerDto customerToCustomerDto(Customer customer);

    @Mapping(source = "firstName", target = "firstName")
    @Mapping(source = "lastName", target = "lastName")
    @Mapping(source = "ssn", target = "ssn")
    @Mapping(source = "phoneNumber", target = "phoneNumber")
    Customer customerCreationDtoToCustomer(CustomerCreationDto customerCreationDto);

    List<CustomerDto> customerListToCustomerListDto(List<Customer> customers);
}
