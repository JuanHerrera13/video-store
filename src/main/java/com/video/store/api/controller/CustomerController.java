package com.video.store.api.controller;

import com.video.store.api.dto.customer.CustomerCreationDto;
import com.video.store.api.dto.customer.CustomerDeleteDto;
import com.video.store.api.dto.customer.CustomerDto;
import com.video.store.api.dto.customer.CustomerUpdateDto;
import com.video.store.domain.service.CustomerService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequiredArgsConstructor
public class CustomerController extends RootController {

    private final CustomerService customerService;

    @GetMapping("/customers")
    @ResponseStatus(HttpStatus.OK)
    public List<CustomerDto> fetchCustomers() {
        return this.customerService.fetchCustomersList();
    }

    @GetMapping("/customers/{firstName}/{lastName}")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto fetchCustomerByFistAndLastName(@Valid @PathVariable String firstName,
                                                      @PathVariable String lastName) {
        return this.customerService.findCustomerByFirstAndLastName(firstName, lastName);
    }

    @PostMapping("/customers.add")
    @ResponseStatus(HttpStatus.CREATED)
    public CustomerDto addCustomer(@Valid @RequestBody CustomerCreationDto customerCreationDto) {
        return this.customerService.addCustomer(customerCreationDto);
    }

    @PutMapping("/customers.update")
    @ResponseStatus(HttpStatus.OK)
    public CustomerDto updateCustomer(@Valid @RequestBody CustomerUpdateDto customerUpdateDto) {
        return this.customerService.updateCustomer(customerUpdateDto);
    }

    @DeleteMapping("/customers.delete")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCustomer(@Valid @RequestBody CustomerDeleteDto customerDeleteDto) {
        this.customerService.deleteCustomer(customerDeleteDto);
    }
}
