package com.daxprotocol.daxp_core_test.daxp;

import org.daxprotocol.core.application.DaxCoreMessages;
import org.daxprotocol.core.annotation.DaxpController;
import org.daxprotocol.core.annotation.DaxpHandler;
import org.daxprotocol.core.model.DaxFrame;
import org.springframework.stereotype.Component;

import static com.daxprotocol.daxp_core_test.daxp.CRMDaxpRegister.CRM_INSERT;

@Component
@DaxpController
public class DaxpProtController {

    public DaxpProtController(){
    }

    @DaxpHandler(DaxCoreMessages.LOG)
    public void daxLog  (DaxFrame incomeFrame, DaxFrame outcomeFrame){

        System.out.println("Log from  Handler test ");

    }
}
