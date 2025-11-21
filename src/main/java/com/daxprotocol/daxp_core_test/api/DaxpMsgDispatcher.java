package com.daxprotocol.daxp_core_test.api;

import com.daxprotocol.daxp_core_test.customer.Customer;
import com.daxprotocol.daxp_core_test.customer.CustomerRepository;
import com.daxprotocol.daxp_core_test.daxp.AppDaxDictionary;
import com.daxprotocol.daxp_core_test.daxp.AppDaxpTag;
import org.daxprotocol.core.conventer.DaxMessageConverter;
import org.daxprotocol.core.factory.DaxMessageFactory;
import org.daxprotocol.core.field.DaxMsgType;
import org.daxprotocol.core.model.DaxMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class DaxpMsgDispatcher {
    @Autowired DaxMessageFactory factory;
    @Autowired AppDaxDictionary appDic;
    @Autowired CustomerRepository customerRepository;

    public DaxMessage dispose(DaxMessage msg) {
        //TODO add filter parameters
        if (msg.getMsgType().compareTo(DaxMsgType.DIC_REQ) == 0) {
            return factory.createDictionaryMsg(appDic);
        }

        if (msg.getMsgType().compareTo(AppMessage.CRM_DATA_REQ) == 0) {
            if (msg.containsField(AppDaxpTag.CUSTOMER_ID)) {
                Long id = Long.valueOf(msg.getStrValue(AppDaxpTag.CUSTOMER_ID));

                Optional<Customer> customer = customerRepository.findById(id);
                if (customer.isPresent()) {
                    return factory.toDaxMessage(AppMessage.CRM_DATA, customer.get());
                }
                return factory.errorResourceNotFound();
            }

            List<Customer> customerList = customerRepository.findAll();
            if (customerList.isEmpty()){
                return factory.errorResourceNotFound();
            }
            return factory.toDaxMessage(AppMessage.CRM_DATA, customerList);

        }
        if (msg.getMsgType().compareTo(AppMessage.CRM_INSERT) == 0) {
            Customer customer = DaxMessageConverter.createFromMessage(msg,Customer.class);
            return factory.toDaxMessage(AppMessage.CRM_DATA, customerRepository.save(customer));
        }

        if (msg.getMsgType().compareTo(AppMessage.CRM_UPDATE) == 0) {
            Long customerId = Long.parseLong(msg.getStrValue(AppDaxpTag.CUSTOMER_ID));

            Optional<Customer> customerOpt = customerRepository.findById(customerId);
            if(customerOpt.isEmpty()){
                return factory.errorResourceNotFound();
            }

            customerOpt.ifPresent(c -> {
                        DaxMessageConverter.setFromMessage(msg, c);
                        customerRepository.save(c);
                    }
            );

            return factory.okMessageType();
        }


        return factory.errorInvalidMessageType(); //TODO in daxp-core define exception

    }
}