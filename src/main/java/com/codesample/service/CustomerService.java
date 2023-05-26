package com.codesample.service;

import com.codesample.dto.order.CustomerCreateUpdateDTO;
import com.codesample.dto.order.CustomerDTO;

public interface CustomerService {

    CustomerDTO createCustomer(CustomerCreateUpdateDTO customerDTO);
}
