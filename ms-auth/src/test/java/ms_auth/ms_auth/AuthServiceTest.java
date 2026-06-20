package ms_auth.ms_auth;

import ms_auth.ms_auth.client.TokenResponse;
import ms_auth.ms_auth.client.UsuarioClient;
import ms_auth.ms_auth.dto.AuthRequest;
import ms_auth.ms_auth.security.JwtProvider;
import ms_auth.ms_auth.service.AuthService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class AuthServiceTest {

    @Mock
    private UsuarioClient usuarioClient;

    @Mock
    private JwtProvider jwtProvider;

    @Mock
    private PasswordEncoder passwordEncoder;

    @InjectMocks
    private AuthService authService;

    private AuthRequest authRequest;
    private AuthRequest mockUser;

    @BeforeEach
    void setUp() {
        authRequest = new AuthRequest();
        authRequest.setEmail("test@test.com");
        authRequest.setPassword("123456");

        mockUser = new AuthRequest();
        mockUser.setEmail("test@test.com");
        mockUser.setPassword("hashedPassword123");
        mockUser.setRol("CLIENTE");
    }

    @Test
    void login_Exitoso() {
        when(usuarioClient.buscarPorEmail(anyString())).thenReturn(mockUser);
        when(passwordEncoder.matches("123456", "hashedPassword123")).thenReturn(true);
        when(jwtProvider.generateToken("test@test.com", "CLIENTE")).thenReturn("mocked-jwt-token");

        TokenResponse response = authService.login(authRequest);

        assertNotNull(response);
        assertEquals("mocked-jwt-token", response.getToken());
    }

    @Test
    void login_FallaPorContrasenaIncorrecta() {
        when(usuarioClient.buscarPorEmail(anyString())).thenReturn(mockUser);
        when(passwordEncoder.matches("123456", "hashedPassword123")).thenReturn(false);

        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> authService.login(authRequest));

        assertEquals("Credenciales inválidas", exception.getMessage());
    }
}
