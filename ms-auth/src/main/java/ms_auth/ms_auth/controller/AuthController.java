package ms_auth.ms_auth.controller;

import jakarta.validation.Valid;
import ms_auth.ms_auth.dto.AuthRequest;
import ms_auth.ms_auth.client.TokenResponse;
import ms_auth.ms_auth.service.AuthService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody AuthRequest request) {
        return ResponseEntity.ok(authService.login(request));
    }
}