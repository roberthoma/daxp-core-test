package com.daxprotocol.daxp_core_test.api;

import com.daxprotocol.daxp_core_test.customer.Customer;
import com.daxprotocol.daxp_core_test.customer.CustomerRepository;
import com.daxprotocol.daxp_core_test.daxp.AppDaxDictionary;
import com.daxprotocol.daxp_core_test.daxp.AppDaxpTag;
import org.daxprotocol.core.conventer.DaxMessageConverter;
import org.daxprotocol.core.factory.DaxMessageFactory;
import org.daxprotocol.core.model.DaxMessage;
import org.daxprotocol.core.model.head.DaxMsgType;
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
            Customer customer = DaxMessageConverter.fromMessage(msg,Customer.class);
            return factory.toDaxMessage(AppMessage.CRM_DATA, customerRepository.save(customer));
        }

        return factory.errorInvalidMessageType(); //TODO in daxp-core define exception

    }
}