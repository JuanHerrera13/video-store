package com.video.store.api.mapping;

import com.video.store.api.dto.customer.CustomerCreationDto;
import com.video.store.api.dto.customer.CustomerDto;
import com.video.store.domain.entity.Customer;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper
public interface CustomerMapper {

    Customer customerDtoToCustomer(CustomerDto customerDto);

    CustomerDto customerToCustomerDto(Customer customer);

    List<CustomerDto> customerListToCustomerListDto(List<Customer> customers);

    Customer customerCreationDtoToCustomer(CustomerCreationDto customerCreationDto);
}
