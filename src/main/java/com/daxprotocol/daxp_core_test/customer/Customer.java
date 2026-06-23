package com.daxprotocol.daxp_core_test.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.daxprotocol.core.annotation.DaxpEntity;
import org.daxprotocol.core.annotation.DaxpField;

import static com.daxprotocol.daxp_core_test.daxp.CRMDaxpRegister.*;

@Data
@Entity
@Builder
@AllArgsConstructor
@DaxpEntity(tagId = CUSTOMER_ENTITY, name = "Customer")
public class Customer {

    @DaxpField(tagId = CUSTOMER_ID)
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long customerId;

    @DaxpField(tagId = CUSTOMER_FIRST_NAME)
    @NotNull
    String name;

    @DaxpField(tagId = CUSTOMER_SURNAME)
    String surname;

    @DaxpField(tagId = CUSTOMER_EMAIL)
    String email;

    @DaxpField(tagId = CUSTOMER_TOWN)
    String town;

    @DaxpField(tagId = CUSTOMER_TELEPHONE)
    String telephone;

    @DaxpField(tagId = CUSTOMER_TYPE)
    CustomerType type;

    public Customer(){
    }

}
