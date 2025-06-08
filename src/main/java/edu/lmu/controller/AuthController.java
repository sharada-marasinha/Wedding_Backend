package edu.lmu.controller;

import edu.lmu.config.JwtTokenProvider;
import edu.lmu.entity.LoginDto;
import edu.lmu.entity.RegisterDto;
import edu.lmu.entity.User;
import edu.lmu.exception.RecordNotFoundException;
import edu.lmu.service.impl.AuthService;
import edu.lmu.util.GeneralUtil;
import edu.lmu.util.UserUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@CrossOrigin
public class AuthController {

    private final AuthService authService;
    private final JwtTokenProvider jwtTokenProvider;

    @GetMapping("/user")
    public ResponseEntity<Optional<User>> getCurrentUser(@RequestHeader("Authorization") String authHeader) {
        String token = authHeader.substring(7); // Remove "Bearer "
        String email = jwtTokenProvider.getEmailFromToken(token);
        Optional<User> user = authService.findByEmail(email);
        return ResponseEntity.ok(user);
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginDto loginDto) {
        String jwt = authService.login(loginDto);
        Map<String, String> response = new HashMap<>();
        response.put("token", jwt); // Key "token" matches what frontend expects

        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody RegisterDto registerDto) throws MessagingException, UnsupportedEncodingException {
        return ResponseEntity.ok(authService.register(registerDto));
    }

    @GetMapping("/activateAccount/{id}")
    public ResponseEntity<String> activateAccount(@PathVariable Long id){
        try {
            authService.updateStatus(id, 1);
            return new ResponseEntity<String>(UserUtil.VERIFICATION_PAGE,HttpStatus.OK);
        }
        catch(Exception exception) {
            throw new RecordNotFoundException(UserUtil.USER_NOT_FOUND);
        }

    }

    @PostMapping("/otp/{email}/{otp}")
    public ResponseEntity<String> verifyOTP(@PathVariable String email,@PathVariable Integer otp){
        if(Objects.equals(authService.getUserByEmail(email).get().getOtp(), otp)) {
            return new ResponseEntity<String>(GeneralUtil.toJson("login with otp successfull"),HttpStatus.OK);
        }
        System.out.println(authService.getUserByEmail(email).get().getOtp()+" "+otp);
        return new ResponseEntity<String>(GeneralUtil.toJson("Invalid OTP"),HttpStatus.OK);
    }
}
