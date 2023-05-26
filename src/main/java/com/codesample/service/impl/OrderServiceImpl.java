package com.codesample.service.impl;

import com.codesample.constants.OrderType;
import com.codesample.domain.Customer;
import com.codesample.domain.Order;
import com.codesample.dto.order.OrderCreateUpdateDTO;
import com.codesample.dto.order.OrderDTO;
import com.codesample.dto.order.OrdersDTO;
import com.codesample.repository.CustomerRepository;
import com.codesample.repository.OrderRepository;
import com.codesample.service.OrderService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;

import static com.codesample.constants.Constants.ErrorMessages.CUSTOMER_NOT_FOUND_MSG;
import static com.codesample.constants.Constants.Order.WINNING_MINUTE;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final CustomerRepository customerRepository;

    private final ModelMapper modelMapper;

    @Override
    public List<OrderDTO> makeOrder(int customerId, OrdersDTO ordersDTO) {

        Customer customer = customerRepository
                .findById(customerId)
                .orElseThrow(() ->
                        new EntityNotFoundException(String.format(CUSTOMER_NOT_FOUND_MSG, customerId)));

        Timestamp currentDate = new Timestamp(System.currentTimeMillis());
        boolean eligibleForWin = checkIfOrderEligibleForWin(ordersDTO, currentDate);
        List<Order> orders = ordersDTO
                .getOrders()
                .stream()
                .map(formOrdersForDbSave(currentDate, eligibleForWin, customer))
                .collect(Collectors.toList());

        List<Order> savedOrders = orderRepository.saveAll(orders);

        return savedOrders
                .stream()
                .map(mapOrderToOrdersDTOs(modelMapper))
                .collect(Collectors.toList());
    }

    private Function<Order, OrderDTO> mapOrderToOrdersDTOs(ModelMapper modelMapper){
        return order -> {
            OrderDTO orderDTO = modelMapper.map(order, OrderDTO.class);
            orderDTO.setCustomerId(order.getCustomer().getId());

            return orderDTO;
        };
    }

    private Function<OrderCreateUpdateDTO, Order> formOrdersForDbSave(Timestamp currentDate,
                                                                      boolean isEligibleForWin,
                                                                      Customer customer) {
        return createUpdateDTO -> {
            Order order = new Order();
            String type = createUpdateDTO.getOrderType().getType();
            order.setOrderType(type);
            order.setDate(currentDate);
            order.setCustomer(customer);

            if (isEligibleForWin && (type.equals(OrderType.PRINTS.getType()) ||
                    type.equals(OrderType.STRIPS.getType()))) {
                order.setPrice(0);
            } else {
                order.setPrice(createUpdateDTO.getOrderType().getPrice());
            }

            return order;
        };
    }

    private boolean checkIfOrderEligibleForWin(OrdersDTO ordersDTO, Timestamp currentDate) {

        List<OrderType> orderTypes = ordersDTO.getOrders()
                .stream()
                .map(OrderCreateUpdateDTO::getOrderType)
                .toList();

        boolean allTypesPresent = new HashSet<>(orderTypes).containsAll(List.of(OrderType.values()));
        int currentMinute = currentDate.toLocalDateTime().getMinute();

        return allTypesPresent && currentMinute == WINNING_MINUTE;
    }

}
