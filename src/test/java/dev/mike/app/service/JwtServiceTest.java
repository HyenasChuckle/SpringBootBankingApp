package dev.mike.app.service;

import dev.mike.app.entity.Customer;
import dev.mike.app.entity.enums.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Generating JWT - Unit Test")
class JwtServiceTest {

    private final String secret = "T6rFCtE0UscOKn2iBDnkai4oS9pY27SfrciowymV4US";
    private final long expiration = 60000;

    private final JwtService jwtService = new JwtService(secret, expiration);

    @Test
    void checkTokenGenerationProcess() {
        Customer customer = Customer.builder()
                .username("example")
                .roles(Set.of(Role.USER))
                .build();

        String token = jwtService.generateToken(customer);

        assertThat(token).isNotBlank();

        // Check the output on jwt.io using specified secret.
        System.out.printf("Token: %s%n", token);
    }
}