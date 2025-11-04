package com.daxprotocol.daxp_core_test.contracts;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.daxprotocol.core.annotation.DaxpDic;
import org.daxprotocol.core.annotation.DaxpTag;

import java.math.BigDecimal;
import static com.daxprotocol.daxp_core_test.daxp.ApplicationDaxpTag.*;

@DaxpDic(name = "Contract",namespace = "cnt")
@Data
@Entity
@Builder
@AllArgsConstructor
public class Contract {

    @DaxpTag(tag = CONTRACT_ID, uiLabel = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @NotNull
    @DaxpTag(tag = CONTRACT_NO,uiLabel = "Contract No")
    @Size(min = 2, max = 20)
    String  contract_no;

    @NotNull
    @DaxpTag(tag = CONTRACT_AMOUNT,uiLabel = "Amount")
    BigDecimal amount;

    @DaxpTag(tag = CONTRACT_STATUS,uiLabel = "Status")
    Character status ;



}
