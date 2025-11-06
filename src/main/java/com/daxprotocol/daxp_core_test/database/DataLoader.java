package com.daxprotocol.daxp_core_test.database;

import com.daxprotocol.daxp_core_test.contracts.Contract;
import com.daxprotocol.daxp_core_test.contracts.ContractRepository;
import com.daxprotocol.daxp_core_test.customer.Customer;
import com.daxprotocol.daxp_core_test.customer.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CustomerRepository customerRepo;
    private final ContractRepository contractRepository;

    @Autowired
    public DataLoader(CustomerRepository customerRepo, ContractRepository contractRepository) {
        this.customerRepo = customerRepo;
        this.contractRepository = contractRepository;
    }

    @Override
    public void run(String... args) {
        customerRepo.save(Customer.builder().name("John").build());
        customerRepo.save(Customer.builder().name("Anna").email("anna@wp.pl").telephone("+48 123 456 789").build());
        customerRepo.save(Customer.builder().name("Robert").surname("Homa").email("robert.homa@daxprotocol.org").build());

        contractRepository.save(Contract.builder().contract_no("1234567876").amount(2000L).customerId(1L).build());
        contractRepository.save(Contract.builder().contract_no("2345678767").amount(4000L).customerId(1L).build());
        contractRepository.save(Contract.builder().contract_no("3456787678").amount(3000L).customerId(3L).build());
    }
}

