package com.daxprotocol.daxp_core_test;

import org.daxprotocol.core.application.DaxEngine;


import org.daxprotocol.core.config.DaxpConfigFactory;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

@Configuration
public class DaxpTestConfiguration {

    @Bean
    DaxEngine  getDaxEngine() {

        Properties properties = DaxpConfigFactory
                .createProperties("daxp.properties");
      return new  DaxEngine(properties);

    }

}
