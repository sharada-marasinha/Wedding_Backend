package edu.lmu.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {
    private String email;
    private String password;
    private String role; // ROLE_ADMIN, ROLE_VENDOR, ROLE_COUPLE
}

