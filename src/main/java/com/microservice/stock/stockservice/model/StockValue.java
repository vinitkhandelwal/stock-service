package com.microservice.stock.stockservice.model;

import java.io.Serializable;

public class StockValue implements Serializable {

    private static int value= 100;
    private String name;

    public StockValue(String name) {
        this.name = name;
    }
}
