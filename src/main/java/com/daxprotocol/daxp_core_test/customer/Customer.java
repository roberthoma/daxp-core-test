package com.daxprotocol.daxp_core_test.customer;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.daxprotocol.core.annotation.DaxpTag;
import org.daxprotocol.core.annotation.DaxpDic;

@DaxpDic(name = "Customer",  namespace = "crm")
@Data
@Entity
public class Customer {




    @DaxpTag(tag = 2001, uiLabel = "Id customer")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Long customerId;

    public static final int FIRST_NAME =  2002;
    @DaxpTag(tag = FIRST_NAME,uiLabel = "First name")
    @NotNull
    String name;

    public static final int SURNAME = 2003;
    @DaxpTag(tag = SURNAME,uiLabel = "Surname")
    String surname;

    @DaxpTag(tag = 2004,uiLabel = "Email")
    String email;

    @DaxpTag(tag = 2005 ,uiLabel = "Town")
    String town;

    @DaxpTag(tag = 2006,uiLabel = "Telephone")
    String telephone;

    public Customer(  ){
    }
    public Customer(  String name){
      this.name = name;
    }

    public Customer(Long customerId, String name) {
        this.customerId = customerId;
        this.name = name;
    }

}
