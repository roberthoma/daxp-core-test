package com.daxprotocol.daxp_core_test.contracts;

import jakarta.annotation.PostConstruct;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.daxprotocol.core.annotation.DaxpEntity;
import org.daxprotocol.core.annotation.DaxpField;

import static com.daxprotocol.daxp_core_test.daxp.CRMDaxpRegister.*;
@DaxpEntity( tagId = CONTRACT_TAG  ,name = "Contract")
@Data
@Entity
@Builder
@AllArgsConstructor
public class Contract {

    @DaxpField(tagId = CONTRACT_ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @DaxpField(tagId = CUSTOMER_ID)
    Long customerId;

    @NotNull
    @DaxpField(tagId = CONTRACT_NO)
    @Size(min = 2, max = 20)
    String contractNo;

    @NotNull
    @DaxpField(tagId = CONTRACT_AMOUNT)
    Long amount;

    @DaxpField(tagId = CONTRACT_STATUS)
    Character status ;

    @PostConstruct
    private void postContract(){
        status = 'I';
    }
    public Contract(){}

}
