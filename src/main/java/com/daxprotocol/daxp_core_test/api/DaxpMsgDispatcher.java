package com.daxprotocol.daxp_core_test.api;

import com.daxprotocol.daxp_core_test.contracts.Contract;
import com.daxprotocol.daxp_core_test.customer.Customer;
import com.daxprotocol.daxp_core_test.customer.CustomerRepository;
import com.daxprotocol.daxp_core_test.daxp.AppDaxDictionary;
import org.daxprotocol.core.factory.DaxMessageFactory;
import org.daxprotocol.core.model.DaxMessage;
import org.daxprotocol.core.model.head.DaxMsgType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
public class DaxpMsgDispatcher {
    @Autowired DaxMessageFactory factory;
    @Autowired AppDaxDictionary appDic;
    @Autowired CustomerRepository customerRepository;

    public DaxMessage dispose(DaxMessage msg) {
        if (msg.getMsgType().compareTo(DaxMsgType.DIC_REQ) == 0) {

            DaxMessage respMsg = factory.createDictionaryMsg(appDic);
            return respMsg;
        }
        if (msg.getMsgType().compareTo("UCDR") == 0) {

            Long custId = Long.valueOf(msg.getBody().getPair(0,2001).getStrValue());

            Optional<Customer> customer = customerRepository.findById(custId);

            DaxMessage respMs;
            if (customer.isPresent()){
                return         factory.toDaxMessage("UCD",customer.get());
            }

            return  factory.createDictionaryReq(); //>>>test
        }

        return null; //TODO bad request

    }
    public DaxMessage disposeByPar(String pasr1) {
        return  factory.createDictionaryReq(); //>>>test
    }
}