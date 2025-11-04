package com.daxprotocol.daxp_core_test.contracts;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.daxprotocol.core.annotation.DaxpDic;
import org.daxprotocol.core.annotation.DaxpTag;

import java.math.BigDecimal;

@DaxpDic(name = "Contract",namespace = "cnt")
@Data
public class Contract {

    @NotNull
    @DaxpTag(tag = 2201, uiLabel = "Id")
    Long id;

    @NotNull
    @DaxpTag(tag = 2203,uiLabel = "Contract No")
    @Size(min = 2, max = 20)
    String  contract_no;

    @DaxpTag(tag = 2202,uiLabel = "Amount")
    BigDecimal amount;

    @DaxpTag(tag = 2205,uiLabel = "Status")
    Character status= 'I';



}
