package com.daxprotocol.daxp_core_test.database;

import com.daxprotocol.daxp_core_test.customer.Customer;
import com.daxprotocol.daxp_core_test.customer.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final CustomerRepository repo;

    public DataLoader(CustomerRepository repo) {
        this.repo = repo;
    }

    @Override
    public void run(String... args) {
        repo.save(new Customer("John"));
        repo.save(new Customer("Anna"));
        Customer customer = new Customer();
        customer.setName("Robert");
        customer.setSurname("Homa");
        customer.setEmail("robert.homa@daxprotocol.org");
        repo.save(customer);




        repo.findAll().forEach(c ->
                System.out.println(c.getCustomerId() + " -> " + c.getName())
        );
    }
}


/*
*
*  //            Customer customer = new Customer();
//            customer.setCustomerId(2);
//            customer.setName("Robert");
//            customer.setSurname("Homa");
//            customer.setEmail("robert.homa@daxprotocol.org");
//            DaxMessage respMsg = factory.toDaxMessage("UCD",customer);
//
*
* */