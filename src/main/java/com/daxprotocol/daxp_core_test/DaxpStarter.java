package com.daxprotocol.daxp_core_test;

import com.daxprotocol.daxp_core_test.daxp.DaxpInit;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DaxpStarter {

    @Autowired
    DaxpInit daxpInit;

    @PostConstruct
    public void init() {
      daxpInit.daxpInitScanner();
    }



}
