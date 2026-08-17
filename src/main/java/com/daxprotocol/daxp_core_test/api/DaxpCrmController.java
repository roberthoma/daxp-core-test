package com.daxprotocol.daxp_core_test.api;

import com.daxprotocol.daxp_core_test.customer.Customer;
import com.daxprotocol.daxp_core_test.customer.CustomerRepository;
import com.daxprotocol.daxp_core_test.daxp.CRMDaxpRegister;
import org.daxprotocol.core.annotation.DaxpController;
import org.daxprotocol.core.annotation.DaxpHandler;
import org.daxprotocol.core.annotation.DaxpMsg;
import org.daxprotocol.core.application.DaxEngine;
import org.daxprotocol.core.model.DaxFrame;
import org.daxprotocol.core.model.DaxMessage;
import org.daxprotocol.core.model.tag.DaxTag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import static com.daxprotocol.daxp_core_test.daxp.CRMDaxpRegister.*;
@DaxpController
@Component
public class DaxpCrmController {
    @Autowired CustomerRepository customerRepository;
    @Autowired DaxEngine daxEngine;

    @DaxpHandler(CRMDaxpRegister.CRM_DATA_REQ)
    public void getBaseCrmData(DaxMessage incomeMsg , DaxFrame outcomeFrame){

        int namespaceId = daxEngine.getConfig().getAppNamespaceId();
        DaxTag customerIdTag = DaxTag.of(namespaceId,CUSTOMER_ID);

        if (incomeMsg.containsField(0, customerIdTag)){
            Long customerId = Long.valueOf(  incomeMsg.get(0, customerIdTag).getIntegerValue());

            Optional<Customer> customerById = customerRepository.findById(customerId);
            if (customerById.isPresent()){
                DaxMessage message = daxEngine.getMessageFactory()
                                              .toDaxMessage(CRMDaxpRegister.CRM_DATA,customerById.get());
                outcomeFrame.addMessage(message);
                outcomeFrame.addMessage(daxEngine.getMessageFactory().okMessage());
                outcomeFrame.addMessage(daxEngine.getMessageFactory().logMessage(2,"Any log from TEST APP"));

            }
            else {
                outcomeFrame.addMessage(daxEngine.getMessageFactory().errorInvalidMessageType());
            }

            return;
        }

        List<Customer> customerList = customerRepository.findAll();
        //Optional<Customer> customerList = customerRepository.findById(2L);

        for (Customer customer : customerList){
            DaxMessage message = daxEngine.getMessageFactory().toDaxMessage(CRMDaxpRegister.CRM_DATA,customer);
            outcomeFrame.addMessage(message);
        }

    }


    @DaxpHandler(CRM_DELETE)
    public void deleteCustomer(DaxMessage incomeMsg, DaxFrame outcomeFrame){
        int namespaceId = daxEngine.getConfig().getAppNamespaceId();
        DaxTag customerIdTag = DaxTag.of(namespaceId,CUSTOMER_ID);

        if (incomeMsg.containsField(0, customerIdTag)){
            Long customerId = Long.valueOf(  incomeMsg.get(0, customerIdTag).getIntegerValue());

            Optional<Customer> customerById = customerRepository.findById(customerId);
            if (customerById.isPresent()){

                customerRepository.deleteById(customerId);
                outcomeFrame.addMessage(daxEngine.getMessageFactory().okMessage());
            }
            else {
                outcomeFrame.addMessage(daxEngine.getMessageFactory().errorInvalidMessageType());
            }

        }

    }

    @DaxpHandler(CRM_UPDATE)
    public void updateCustomer(DaxMessage incomeMsg, DaxFrame outcomeFrame){

        int namespaceId = daxEngine.getConfig().getAppNamespaceId();
        DaxTag customerIdTag = DaxTag.of(namespaceId,CUSTOMER_ID);

        if (incomeMsg.containsField(0, customerIdTag)){
            Long customerId = Long.valueOf(  incomeMsg.get(0, customerIdTag).getIntegerValue());

            Optional<Customer> customerById = customerRepository.findById(customerId);
            if (customerById.isPresent()){

                Customer customer = customerById.get();

                daxEngine.getMessageConverter().updateFromMessage(incomeMsg, customer);

                customerRepository.save(customer);

            outcomeFrame.addMessage(daxEngine.getMessageFactory().okMessage());
            }
            else {
                outcomeFrame.addMessage(daxEngine.getMessageFactory().errorInvalidMessageType());
            }

        }

    }

    @DaxpHandler(CRM_INSERT)
    public void insertCustomer(DaxMessage incomeMsg, DaxFrame outcomeFrame){

        int namespaceId = daxEngine.getConfig().getAppNamespaceId();
//        DaxTag customerIdTag = DaxTag.of(contextId,CUSTOMER_ID);


            Customer customer = daxEngine.getMessageConverter()
                                         .createFromMessage(incomeMsg, Customer.class);
            Customer after = customerRepository.save(customer);

            outcomeFrame.addMessage(daxEngine.getMessageFactory()
                                             .toDaxMessage(CRMDaxpRegister.CRM_DATA,after));


            outcomeFrame.addMessage(daxEngine.getMessageFactory().okMessage());

        }





}