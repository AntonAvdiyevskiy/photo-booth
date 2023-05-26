package com.codesample.service;

import com.codesample.dto.order.OrderDTO;
import com.codesample.dto.order.OrdersDTO;

import java.util.List;

public interface OrderService {

    List<OrderDTO> makeOrder(int customerId, OrdersDTO ordersDTO);
}
