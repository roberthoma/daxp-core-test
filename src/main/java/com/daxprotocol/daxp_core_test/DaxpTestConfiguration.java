package com.daxprotocol.daxp_core_test;

import org.daxprotocol.core.codec.DaxMessageCodec;
import org.daxprotocol.core.dictionary.DaxDictionaryManager;
import org.daxprotocol.core.factory.DaxMessageFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DaxpTestConfiguration {

    @Bean
    DaxMessageCodec getDaxMessageCodec(){
        return new DaxMessageCodec();
    }

    @Bean
    DaxMessageFactory getDaxMessageFactory(){
       return new DaxMessageFactory();

    }

    @Bean
    DaxDictionaryManager getDaxDictionaryManager(){
        return new DaxDictionaryManager();
    }

}
