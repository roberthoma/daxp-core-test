package com.daxprotocol.daxp_core_test.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.daxprotocol.core.annotation.DaxpTag;
import org.daxprotocol.core.annotation.DaxpDic;

import static com.daxprotocol.daxp_core_test.daxp.ApplicationDaxpTag.*;

@DaxpDic(name = "Customer",  namespace = "crm")
@Data
@Entity
@Builder
@AllArgsConstructor
public class Customer {

    @DaxpTag(tag = CUSTOMER_ID, uiLabel = "Id customer")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long customerId;

    @DaxpTag(tag = CUSTOMER_FIRST_NAME,uiLabel = "First name")
    @NotNull
    String name;

    @DaxpTag(tag = CUSTOMER_SURNAME,uiLabel = "Surname")
    String surname;

    @DaxpTag(tag = CUSTOMER_EMAIL, uiLabel = "Email")
    String email;

    @DaxpTag(tag = CUSTOMER_TOWN,uiLabel = "Town")
    String town;

    @DaxpTag(tag = CUSTOMER_TELEPHONE, uiLabel = "Telephone")
    String telephone;

    public Customer(){
    }

}
