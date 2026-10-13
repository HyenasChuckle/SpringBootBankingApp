package dev.mike.app.filter;

import dev.mike.app.entity.Customer;
import dev.mike.app.entity.enums.Role;
import dev.mike.app.service.JwtService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.io.IOException;
import java.util.Set;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("JwtAuthorizationFilterTest class unit tests")
class JwtAuthorizationFilterTest {
    @Mock
    private HttpServletRequest request;

    @Mock
    private HttpServletResponse response;

    @Mock
    private FilterChain filterChain;

    private final String secret = "T6rFCtE0UscOKn2iBDnkai4oS9pY27SfrciowymV4US";

    private final JwtService jwtService = new JwtService(secret, 60000);

    private final JwtAuthorizationFilter filter = new JwtAuthorizationFilter(secret);

    @DisplayName("doFilterInternal() method tests")
    @Nested
    class DoFilterInternalTests {
        @Test
        @DisplayName("should perform customer authorization")
        void shouldPerformCustomerAuthorization() throws ServletException, IOException {
            String token = generateToken();

            // Given (Arrange):
            when(request.getHeader("Authorization")).thenReturn(token);

            // When (Act):
            filter.doFilterInternal(request, response, filterChain);

            // Then (Assert):
            verify(filterChain, times(1)).doFilter(request, response);
        }

        // Convenience method for generating the token.
        private String generateToken() {
            Customer customer = Customer.builder()
                    .username("example")
                    .roles(Set.of(Role.USER))
                    .build();

            return jwtService.generateToken(customer);
        }
    }
}