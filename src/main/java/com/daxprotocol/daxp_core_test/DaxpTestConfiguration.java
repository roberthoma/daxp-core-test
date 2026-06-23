package com.daxprotocol.daxp_core_test;

import org.daxprotocol.core.application.DaxEngine;


import org.daxprotocol.core.config.DaxpConfigFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaxpTestConfiguration {

    @Bean
    DaxEngine  getDaxEngine() {
      return new  DaxEngine(DaxpConfigFactory
                .createConfig(DaxpConfigFactory
                        .createProperties("daxp.properties")));

    }

}
