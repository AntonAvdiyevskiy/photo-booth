package com.codesample.constants;

import lombok.Getter;

@Getter
public enum OrderType {
    PRINTS("PRINTS", 5L),
    PANORAMAS("PANORAMAS", 7L),
    STRIPS("STRIPS", 5L);

    private String type;

    private double price;

    OrderType(String type, double price) {
        this.type = type;
        this.price = price;
    }
}
