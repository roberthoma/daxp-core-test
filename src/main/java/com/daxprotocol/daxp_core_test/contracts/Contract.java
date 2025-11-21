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
import org.daxprotocol.core.annotation.DaxpField;
import org.daxprotocol.core.annotation.DaxpFieldGroup;
import org.daxprotocol.core.annotation.DaxpFieldReference;

import static com.daxprotocol.daxp_core_test.daxp.AppDaxpTag.*;
import static com.daxprotocol.daxp_core_test.daxp.AppDaxpGroup.*;

@DaxpFieldGroup(id = GROUP_CONTRACT , name = "Contract",namespace = "cnt")
@Data
@Entity
@Builder
@AllArgsConstructor
public class Contract {

    @DaxpField(tag = CONTRACT_ID, uiLabel = "Id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long id;

    @DaxpFieldReference(tag = CUSTOMER_ID)
    Long customerId;

    @NotNull
    @DaxpField(tag = CONTRACT_NO,uiLabel = "Contract No")
    @Size(min = 2, max = 20)
    String  contract_no;

    @NotNull
    @DaxpField(tag = CONTRACT_AMOUNT,uiLabel = "Amount")
    Long amount;

    @DaxpField(tag = CONTRACT_STATUS,uiLabel = "Status")
    Character status ;

    @PostConstruct
    private void postContract(){
        status = 'I';
    }

}
