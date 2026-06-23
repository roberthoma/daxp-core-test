package com.daxprotocol.daxp_core_test.api;

import com.daxprotocol.daxp_core_test.customer.Customer;
import com.daxprotocol.daxp_core_test.customer.CustomerRepository;
import com.daxprotocol.daxp_core_test.daxp.CRMDaxpRegister;
import org.daxprotocol.core.annotation.DaxpController;
import org.daxprotocol.core.annotation.DaxpHandler;
import org.daxprotocol.core.application.DaxEngine;
import org.daxprotocol.core.model.DaxFrame;
import org.daxprotocol.core.model.DaxMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@DaxpController
@Component
public class DaxpCrmController {
    @Autowired CustomerRepository customerRepository;
    @Autowired DaxEngine daxEngine;

    @DaxpHandler(CRMDaxpRegister.CRM_DATA_REQ)
    public void getBaseCrmData(DaxFrame incomeFrame, DaxFrame outcomeFrame){
        System.out.println("tetette handler");

        List<Customer> customerList = customerRepository.findAll();
        //Optional<Customer> customerList = customerRepository.findById(2L);

        for (Customer customer : customerList){
            DaxMessage message = daxEngine.getMessageFactory().toDaxMessage("CRM.DATA",customer);
            outcomeFrame.addMessage(message);
        }

    }
}