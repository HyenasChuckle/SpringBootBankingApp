package dev.mike.app.service;

import dev.mike.app.dto.SignUpRequestDto;
import dev.mike.app.entity.Customer;
import dev.mike.app.repository.CustomerRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public Customer create(SignUpRequestDto dto) {
        String encodedPassword = passwordEncoder.encode(dto.password());

        Customer customer = Customer.builder()
                .username(dto.username())
                .password(encodedPassword)
                .build();

        return customerRepository.save(customer);
    }
}
