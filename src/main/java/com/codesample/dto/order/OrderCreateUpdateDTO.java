package com.codesample.dto.order;

import com.codesample.constants.OrderType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class OrderCreateUpdateDTO {

    private OrderType orderType;


}
