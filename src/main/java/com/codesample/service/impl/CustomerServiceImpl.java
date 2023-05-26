package com.codesample.service.impl;

import com.codesample.domain.Customer;
import com.codesample.dto.order.CustomerCreateUpdateDTO;
import com.codesample.dto.order.CustomerDTO;
import com.codesample.repository.CustomerRepository;
import com.codesample.service.CustomerService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper;

    @Override
    public CustomerDTO createCustomer(CustomerCreateUpdateDTO customerDTO) {
        Customer customer = customerRepository.save(modelMapper.map(customerDTO, Customer.class));

        return modelMapper.map(customer, CustomerDTO.class);
    }
}
