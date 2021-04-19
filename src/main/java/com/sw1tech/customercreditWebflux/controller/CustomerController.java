package com.sw1tech.customercreditWebflux.controller;

import com.sw1tech.customercreditWebflux.controllerDocs.ICustomerControllerDoc;
import com.sw1tech.customercreditWebflux.dto.CustomerDto;
import com.sw1tech.customercreditWebflux.exception.CustomerAlreadyRegisteredException;
import com.sw1tech.customercreditWebflux.exception.CustomerLimitCreditOutOfRangeException;
import com.sw1tech.customercreditWebflux.exception.CustomerNotFoundException;
import com.sw1tech.customercreditWebflux.service.CustomerService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/v1/customers")
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerController implements ICustomerControllerDoc {

    private final CustomerService customerService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @Override
    public Mono<CustomerDto> createCustomer(CustomerDto customerDto) throws CustomerAlreadyRegisteredException, CustomerLimitCreditOutOfRangeException {
        return customerService.createCustomer(customerDto);
    }

    @GetMapping
    @Override
    public Flux<CustomerDto> listCustomers() {
        return customerService.listAll();
    }

    @GetMapping("/{name}")
    @Override
    public Mono<CustomerDto> findByNameCustomer(String name) throws CustomerNotFoundException {
        return customerService.findByName(name);
    }

}
