package ms_auth.ms_auth.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;

@Component
public class JwtProvider {

    // Esta es tu firma secreta. (En la vida real se pone en el application.properties)
    private final String SECRET_WORD = "MiClaveSuperSecretaYMuyLargaParaQueNadieLaAdivine123456789";
    private final SecretKey key = Keys.hmacShaKeyFor(SECRET_WORD.getBytes());

    public String generateToken(String email, String rol) {
        return Jwts.builder()
                .subject(email) // El "dueño" del token
                .claim("rol", rol) // Guardamos el rol dentro del token
                .issuedAt(new Date()) // Fecha de creación
                .expiration(new Date(System.currentTimeMillis() + 3600000)) // Expira en 1 hora
                .signWith(key) // Lo firmamos criptográficamente
                .compact();
    }
}