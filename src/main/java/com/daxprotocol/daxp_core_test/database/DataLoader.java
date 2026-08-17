package com.daxprotocol.daxp_core_test.database;

import com.daxprotocol.daxp_core_test.contracts.Contract;
import com.daxprotocol.daxp_core_test.contracts.ContractRepository;
import com.daxprotocol.daxp_core_test.customer.Customer;
import com.daxprotocol.daxp_core_test.customer.CustomerRepository;
import com.daxprotocol.daxp_core_test.customer.CustomerType;
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
       Customer cust;
        Contract cntr;
        cust = Customer.builder().name("Robert").surname("Homa").email("robert.homa@daxprotocol.org").build();
        cntr = Contract.builder().contractNo("1234567876").amount(2000L).customerId(cust.getCustomerId()).build();
        cust.addContract(cntr);

        customerRepo.save(cust);
        customerRepo.save(Customer.builder().name("Anna").email("anna@wp.pl").telephone("+48 123 456 789").build());
        customerRepo.save(Customer.builder().name("MBank").type(CustomerType.ORGANIZATION).build());
        customerRepo.save(Customer.builder().name("Adam").email("anna@wp.pl").telephone("+48 453646").build());
        customerRepo.save(Customer.builder().name("Kasia").surname("Nowak").email("kasia.nowak@daxprotocol.org").build());

        contractRepository.save(Contract.builder().contractNo("1234567876").amount(2000L).customerId(1L).build());
        contractRepository.save(Contract.builder().contractNo("2345678767").amount(4000L).customerId(1L).build());
        contractRepository.save(Contract.builder().contractNo("3456787678").amount(3000L).customerId(3L).build());
    }
}

