package com.microservice.stock.stockservice.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class StockValue implements Serializable {

    @JsonProperty(value="price")
    private static int value= 100;
    @JsonProperty(value="name")
    private String name;

    public StockValue(String name) {
        this.name = name;
    }
}
