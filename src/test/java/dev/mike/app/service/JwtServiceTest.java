package dev.mike.app.service;

import dev.mike.app.entity.Customer;
import dev.mike.app.entity.enums.Role;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.assertj.core.api.Assertions.*;

@DisplayName("JwtService class unit tests")
class JwtServiceTest {

    private final JwtService jwtService =
            new JwtService("T6rFCtE0UscOKn2iBDnkai4oS9pY27SfrciowymV4US", 60000);

    @Test
    @DisplayName("should generate a valid JWT token")
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