package dev.mike.app.service;

import dev.mike.app.entity.Customer;
import dev.mike.app.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer create(Customer customer) {

        return customerRepository.save(customer);
    }
}
