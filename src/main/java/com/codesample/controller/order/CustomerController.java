package com.codesample.controller.order;

import com.codesample.dto.order.CustomerCreateUpdateDTO;
import com.codesample.dto.order.CustomerDTO;
import com.codesample.dto.order.OrderCreateUpdateDTO;
import com.codesample.dto.order.OrderDTO;
import com.codesample.dto.order.OrdersDTO;
import com.codesample.service.CustomerService;
import com.codesample.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/photo-booth/customer")
@RequiredArgsConstructor
public class CustomerController {

    private final CustomerService customerService;

    private final OrderService orderService;

    @PostMapping(value = "", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Add new customer to system"
    )
    public ResponseEntity<CustomerDTO> createCustomer(@RequestBody CustomerCreateUpdateDTO customerDTO) {
        return ResponseEntity.status(CREATED).body(customerService.createCustomer(customerDTO));
    }

    @PostMapping(value = "/{customerId}/order", produces = MediaType.APPLICATION_JSON_VALUE)
    @Operation(
            description = "Make an order"
    )
    public ResponseEntity<List<OrderDTO>> createCustomerOrder(@PathVariable int customerId, @RequestBody OrdersDTO ordersDTO) {

        return ResponseEntity.status(CREATED).body(orderService.makeOrder(customerId, ordersDTO));

    }
}
