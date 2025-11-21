package com.daxprotocol.daxp_core_test.customer;

public enum CustomerType {

    INDIVIDUAL("I","Natural Person"),
    ORGANIZATION("O", "Legal Entity");

    public final String symbol;
    public final String desc;

    CustomerType(String symbol, String desc){
        this.symbol = symbol;
        this.desc = desc;

    }
 }
