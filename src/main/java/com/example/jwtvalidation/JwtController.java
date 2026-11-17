
package com.example.jwtvalidation;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import io.jsonwebtoken.Claims;

@RestController
@RequestMapping("/api")
public class JwtController {

    private final JwtService jwtService;

    public JwtController(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    // Generate Token
    @GetMapping("/generate")
    public String generateToken(
            @RequestParam String username) {

        return jwtService.generateToken(username);
    }

    // Validate Token and Extract Claims
    @GetMapping("/validate")
    public ResponseEntity<?> validateToken(
            @RequestParam String token) {

        try {
            Claims claims =
                jwtService.validateToken(token);

            return ResponseEntity.ok(claims);

        } catch (Exception e) {

            return ResponseEntity
                .status(401)
                .body("Invalid or expired token");
        }
    }
}
