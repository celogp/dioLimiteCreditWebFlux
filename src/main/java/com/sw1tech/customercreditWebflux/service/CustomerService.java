package com.sw1tech.customercreditWebflux.service;

import com.sw1tech.customercreditWebflux.dto.CustomerDto;
import com.sw1tech.customercreditWebflux.entity.Customer;
import com.sw1tech.customercreditWebflux.exception.CustomerAlreadyRegisteredException;
import com.sw1tech.customercreditWebflux.exception.CustomerLimitCreditOutOfRangeException;
import com.sw1tech.customercreditWebflux.exception.CustomerNotFoundException;
import com.sw1tech.customercreditWebflux.mapper.CustomerMapper;
import com.sw1tech.customercreditWebflux.repository.CustomerRepository;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class CustomerService {
    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper = CustomerMapper.INSTANCE;

    private void verifyIfLimitCreditIsValid(int id, double limitCredit) throws CustomerLimitCreditOutOfRangeException {
        if ((limitCredit < 0.00) || (limitCredit > 1000.00 )) {
            throw new CustomerLimitCreditOutOfRangeException(id, limitCredit);
        }
    }

    private Customer verifyIfExists(int id) throws CustomerNotFoundException {
        return customerRepository.findById(id)
                .orElseThrow(() -> new CustomerNotFoundException(id));
    }

    public Mono<CustomerDto> createCustomer(CustomerDto customerDto) throws CustomerAlreadyRegisteredException,
            CustomerLimitCreditOutOfRangeException {

        verifyIfLimitCreditIsValid(0, customerDto.getLimitcredit() );

        Customer customer = customerMapper.toModel(customerDto);
        Customer savedCustomer = customerRepository.save(customer);
        return Mono.justOrEmpty(customerMapper.toDto(savedCustomer));
    }

    public Flux<CustomerDto> listAll() {
        return Flux.fromIterable(customerRepository.findAll()
                .stream()
                .map(customerMapper::toDto)
                .collect(Collectors.toList()));
    }

    public Mono<CustomerDto> findByName(String name) throws CustomerNotFoundException {
        Customer foundCustomer = customerRepository.findByName(name)
                .orElseThrow(() -> new CustomerNotFoundException(name));
        return Mono.justOrEmpty( customerMapper.toDto(foundCustomer));
    }

}
