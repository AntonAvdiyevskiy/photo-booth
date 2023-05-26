package com.codesample.service.impl;

import com.codesample.domain.Customer;
import com.codesample.dto.order.CustomerCreateUpdateDTO;
import com.codesample.dto.order.CustomerDTO;
import com.codesample.repository.CustomerRepository;
import com.codesample.service.CustomerService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


public class CustomerServiceImplTests {

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ModelMapper modelMapper;

    private CustomerService customerService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        customerService = new CustomerServiceImpl(customerRepository, modelMapper);
    }

    @Test
    public void testCreateCustomer() {

        CustomerCreateUpdateDTO customerCreateUpdateDTO = new CustomerCreateUpdateDTO();
        Customer customer = new Customer();
        CustomerDTO expectedCustomerDTO = new CustomerDTO();

        when(modelMapper.map(customerCreateUpdateDTO, Customer.class)).thenReturn(customer);
        when(customerRepository.save(customer)).thenReturn(customer);
        when(modelMapper.map(customer, CustomerDTO.class)).thenReturn(expectedCustomerDTO);

        CustomerDTO result = customerService.createCustomer(customerCreateUpdateDTO);

        verify(modelMapper).map(customerCreateUpdateDTO, Customer.class);
        verify(customerRepository).save(customer);
        verify(modelMapper).map(customer, CustomerDTO.class);
        assertEquals(expectedCustomerDTO, result);
    }
}