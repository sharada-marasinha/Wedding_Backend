package edu.lmu.config; // Or your actual package name

import edu.lmu.entity.User; // Assuming this User class has getEmail() and getRole()
import io.jsonwebtoken.*;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import io.jsonwebtoken.security.SignatureException; // For specific catch block
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import java.security.Key;
import java.util.Date;

@Component
public class JwtTokenProvider {

    private static final Logger logger = LoggerFactory.getLogger(JwtTokenProvider.class);

    @Value("${app.jwt-secret}")
    private String jwtSecretString;

    @Value("${app.jwt-expiration-milliseconds}")
    private long jwtExpirationMs;

    private Key key;

    @PostConstruct
    public void init() {
        logger.info("Initializing JWTTokenProvider...");
        if (this.jwtSecretString == null || this.jwtSecretString.trim().isEmpty()) {
            logger.error("FATAL: JWT secret string ('app.jwt-secret') is not configured!");
            throw new IllegalArgumentException("JWT secret ('app.jwt-secret') cannot be null or empty. Please configure it properly.");
        }
        logger.debug("Attempting to decode JWT secret from config. Raw string length: {}", this.jwtSecretString.length());

        byte[] keyBytes;
        try {
            keyBytes = Decoders.BASE64.decode(this.jwtSecretString);
        } catch (Exception e) {
            logger.error("FATAL: Failed to decode Base64 JWT secret string. Ensure 'app.jwt-secret' is a valid Base64 encoded string.", e);
            throw new IllegalArgumentException("Failed to decode Base64 JWT secret string. Is 'app.jwt-secret' valid Base64?", e);
        }

        logger.info("DECODED JWT secret key length in bytes: {}", keyBytes.length);

        // For HS512, the key length MUST be at least 512 bits (64 bytes).
        if (keyBytes.length < 64) {
            String errorMessage = String.format(
                    "FATAL: Decoded JWT secret key is too short! Length is %d bytes, but HS512 requires at least 64 bytes. " +
                            "Please ensure 'app.jwt-secret' is a Base64 encoded string of a key with at least 512 bits (64 bytes).",
                    keyBytes.length
            );
            logger.error(errorMessage);
            throw new IllegalArgumentException(errorMessage);
        }

        this.key = Keys.hmacShaKeyFor(keyBytes);
        logger.info("Successfully initialized JWT signing key for HS512 algorithm.");
    }

    public String generateToken(Authentication authentication) {
        // Ensure principal is of expected type. Adjust if using Spring's UserDetails directly.
        User userPrincipal;
        if (authentication.getPrincipal() instanceof User) {
            userPrincipal = (User) authentication.getPrincipal();
        } else {
            logger.error("Authentication principal is not an instance of edu.lmu.entity.User. It is: {}",
                    authentication.getPrincipal().getClass().getName());
            throw new IllegalArgumentException("Authentication principal is not of expected type 'User'");
        }

        String username = userPrincipal.getEmail(); // Or getUsername()
        // Consider fetching roles from authentication.getAuthorities() for more standard Spring Security integration
        String role = userPrincipal.getRole() != null ? userPrincipal.getRole().toString() : ""; // Handle null role

        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + jwtExpirationMs);

        logger.debug("Generating JWT for user: {}, roles: {}, expiration: {}", username, role, expiryDate);

        return Jwts.builder()
                .setSubject(username)
                .claim("role", role)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512) // Explicitly state algorithm with the key
                .compact();
    }

    public String getEmailFromToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            logger.warn("Attempted to get email from null or empty token.");
            return null;
        }
        try {
            Claims claims = Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token)
                    .getBody();
            return claims.getSubject();
        } catch (JwtException e) { // Catching generic JwtException for parsing issues
            logger.error("Error parsing JWT to get email: {}. Token: {}", e.getMessage(), token);
            return null; // Or rethrow as a custom exception
        }
    }

    public boolean validateToken(String token) {
        if (token == null || token.trim().isEmpty()) {
            logger.warn("Attempted to validate null or empty token.");
            return false;
        }
        try {
            Jwts.parserBuilder()
                    .setSigningKey(key)
                    .build()
                    .parseClaimsJws(token);
            logger.trace("Token validated successfully: {}", token);
            return true;
        } catch (SignatureException ex) {
            logger.error("Invalid JWT signature: {}. Token: {}", ex.getMessage(), token);
        } catch (MalformedJwtException ex) {
            logger.error("Invalid JWT token (malformed): {}. Token: {}", ex.getMessage(), token);
        } catch (ExpiredJwtException ex) {
            logger.warn("Expired JWT token: {}. Token: {}", ex.getMessage(), token);
        } catch (UnsupportedJwtException ex) {
            logger.error("Unsupported JWT token: {}. Token: {}", ex.getMessage(), token);
        } catch (IllegalArgumentException ex) {
            logger.error("JWT claims string is empty or invalid: {}. Token: {}", ex.getMessage(), token);
        }
        return false;
    }
}