package com.sw1tech.customercreditWebflux.controllerDocs;

import com.sw1tech.customercreditWebflux.dto.CustomerDto;
import com.sw1tech.customercreditWebflux.exception.CustomerAlreadyRegisteredException;
import com.sw1tech.customercreditWebflux.exception.CustomerLimitCreditOutOfRangeException;
import com.sw1tech.customercreditWebflux.exception.CustomerNotFoundException;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

public interface ICustomerControllerDoc {

    Mono<CustomerDto> createCustomer(@RequestBody @Valid CustomerDto customerDto) throws CustomerAlreadyRegisteredException, CustomerLimitCreditOutOfRangeException;
    Flux<CustomerDto> listCustomers();
    Mono<CustomerDto> findByNameCustomer(@PathVariable String name) throws CustomerNotFoundException;
}
