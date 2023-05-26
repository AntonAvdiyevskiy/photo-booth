package com.codesample.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class OrdersDTO {
    private List<OrderCreateUpdateDTO> orders;
}
