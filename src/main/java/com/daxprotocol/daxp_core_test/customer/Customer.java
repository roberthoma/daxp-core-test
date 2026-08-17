package com.daxprotocol.daxp_core_test.customer;

import com.daxprotocol.daxp_core_test.contracts.Contract;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.daxprotocol.core.annotation.DaxpEntity;
import org.daxprotocol.core.annotation.DaxpField;

import java.util.HashMap;
import java.util.Map;

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
  //  @NotNull
    String surname;

    @DaxpField(tagId = CUSTOMER_EMAIL)
    String email;

    @DaxpField(tagId = CUSTOMER_TOWN)
    String town;

    @DaxpField(tagId = CUSTOMER_TELEPHONE)
    String telephone;

    @DaxpField(tagId = CUSTOMER_TYPE)
    CustomerType type;


    @DaxpField(tagId = CUSTOMER_MAP)
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @JoinColumn(name = "customerId") // Tworzy klucz obcy w tabeli kontraktów, unikając tabeli pośredniczącej
    @MapKeyColumn(name = "contractNo") // Kolumna w tabeli kontraktów przechowująca klucz z mapy (String)
    Map<String, Contract> contractMap;

    public Customer(){
        contractMap = new HashMap<>();
    }

    public void addContract(Contract contract){
        if(contractMap == null){
            contractMap = new HashMap<>();
        }
        contractMap.put(contract.getContractNo(), contract);
    }


}
