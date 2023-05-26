package com.codesample.service.impl;

import com.codesample.constants.OrderType;
import com.codesample.domain.Customer;
import com.codesample.domain.Order;
import com.codesample.dto.order.OrderCreateUpdateDTO;
import com.codesample.dto.order.OrderDTO;
import com.codesample.dto.order.OrdersDTO;
import com.codesample.repository.CustomerRepository;
import com.codesample.repository.OrderRepository;
import jakarta.persistence.EntityNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.modelmapper.ModelMapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class OrderServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private CustomerRepository customerRepository;

    @Mock
    private ModelMapper modelMapper;

    private OrderServiceImpl orderService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        orderService = new OrderServiceImpl(orderRepository, customerRepository, modelMapper);
    }

    @Test
    void makeOrder_WithValidCustomerIdAndOrdersDTO_ReturnsListOfOrderDTO() {
        int customerId = 1;
        OrdersDTO ordersDTO = new OrdersDTO();
        OrderCreateUpdateDTO orderCreateUpdateDTO = new OrderCreateUpdateDTO();
        orderCreateUpdateDTO.setOrderType(OrderType.PRINTS);
        List<OrderCreateUpdateDTO> orderCreateUpdateDTOs = new ArrayList<>();
        orderCreateUpdateDTOs.add(orderCreateUpdateDTO);
        ordersDTO.setOrders(orderCreateUpdateDTOs);

        Customer customer = new Customer();
        customer.setId(customerId);
        when(customerRepository.findById(customerId)).thenReturn(Optional.of(customer));

        List<Order> savedOrders = new ArrayList<>();
        Order order = new Order();
        order.setCustomer(customer);
        savedOrders.add(order);
        when(orderRepository.saveAll(anyList())).thenReturn(savedOrders);

        when(modelMapper.map(any(Order.class), eq(OrderDTO.class))).thenReturn(new OrderDTO());

        List<OrderDTO> result = orderService.makeOrder(customerId, ordersDTO);

        assertNotNull(result);
        assertEquals(savedOrders.size(), result.size());
        verify(customerRepository, times(1)).findById(customerId);
        verify(orderRepository, times(1)).saveAll(anyList());
        verify(modelMapper, times(savedOrders.size())).map(any(Order.class), eq(OrderDTO.class));
    }

    @Test
    void makeOrder_WithInvalidCustomerId_ThrowsEntityNotFoundException() {

        OrderCreateUpdateDTO orderCreateUpdateDTO = new OrderCreateUpdateDTO();
        orderCreateUpdateDTO.setOrderType(OrderType.PRINTS);
        int customerId = 1;
        OrdersDTO ordersDTO = new OrdersDTO();
        List<OrderCreateUpdateDTO> orderCreateUpdateDTOs = new ArrayList<>();
        orderCreateUpdateDTOs.add(orderCreateUpdateDTO);
        ordersDTO.setOrders(orderCreateUpdateDTOs);

        when(customerRepository.findById(customerId)).thenReturn(Optional.empty());

        assertThrows(EntityNotFoundException.class, () -> orderService.makeOrder(customerId, ordersDTO));

        verify(customerRepository, times(1)).findById(customerId);
        verify(orderRepository, never()).saveAll(anyList());
        verify(modelMapper, never()).map(any(Order.class), eq(OrderDTO.class));
    }
}
