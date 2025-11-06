package com.daxprotocol.daxp_core_test.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.daxprotocol.core.annotation.DaxpField;
import org.daxprotocol.core.annotation.DaxpDic;

import static com.daxprotocol.daxp_core_test.daxp.AppDaxpTag.*;

@DaxpDic(name = "Customer",  namespace = "crm")
@Data
@Entity
@Builder
@AllArgsConstructor
public class Customer {

    @DaxpField(tag = CUSTOMER_ID, uiLabel = "Id customer")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long customerId;

    @DaxpField(tag = CUSTOMER_FIRST_NAME,uiLabel = "First name")
    @NotNull
    String name;

    @DaxpField(tag = CUSTOMER_SURNAME,uiLabel = "Surname")
    String surname;

    @DaxpField(tag = CUSTOMER_EMAIL, uiLabel = "Email")
    String email;

    @DaxpField(tag = CUSTOMER_TOWN,uiLabel = "Town")
    String town;

    @DaxpField(tag = CUSTOMER_TELEPHONE, uiLabel = "Telephone")
    String telephone;

    public Customer(){
    }

}
