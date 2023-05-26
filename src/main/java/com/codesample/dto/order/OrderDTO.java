package com.codesample.dto.order;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Timestamp;

@Getter
@Setter
@NoArgsConstructor
public class OrderDTO extends OrderCreateUpdateDTO {

    private String id;

    private double price;

    private int customerId;

    private Timestamp date;
}
