package com.daxprotocol.daxp_core_test.customer;

import com.daxprotocol.daxp_core_test.daxp.CRMDaxpRegister;
import org.daxprotocol.core.annotation.DaxpCollection;

@DaxpCollection(tagId = CRMDaxpRegister.TEST_COLLECTION_ENUM_6001, description = "Org enum name ")
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
