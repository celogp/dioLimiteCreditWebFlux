package com.sw1tech.customercreditWebflux.mapper;

import com.sw1tech.customercreditWebflux.dto.CustomerDto;
import com.sw1tech.customercreditWebflux.entity.Customer;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CustomerMapper {

    CustomerMapper INSTANCE = Mappers.getMapper(CustomerMapper.class);

    Customer toModel(CustomerDto customerDto);

    CustomerDto toDto(Customer customer);

}
