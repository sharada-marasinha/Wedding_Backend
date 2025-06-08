package edu.lmu.service.impl;

import edu.lmu.config.JwtTokenProvider;
import edu.lmu.entity.LoginDto;
import edu.lmu.entity.RegisterDto;
import edu.lmu.entity.User;
import edu.lmu.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.UnsupportedEncodingException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthenticationManager authManager;
    private final JwtTokenProvider jwtProvider;
    private final UserRepository userRepo;
    private final PasswordEncoder encoder;
    @Autowired
    EmailService emailService;

    public String login(LoginDto dto) {
        Authentication authentication = authManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        dto.getEmail(),
                        dto.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return jwtProvider.generateToken(authentication);
    }

    public String register(RegisterDto dto) throws MessagingException, UnsupportedEncodingException {

        if (userRepo.existsByEmail(dto.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(encoder.encode(dto.getPassword()));
        user.setRole(dto.getRole());
        User save = userRepo.save(user);

        emailService.sendVerificationEmail(user, "http://localhost:8080/user/activateAccount/"+save.getId());
        return "User registered successfully!";
    }

    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    public boolean updateStatus(Long id,int status) {
        User user = userRepo.findById(id).get();
        user.setStatus(status);
        userRepo.save(user);

        return true;
    }

    public boolean updateLoginAttempts(Long id,int attempts) {
        User user = userRepo.findById(id).get();
        user.setLoginAttempts(attempts);
        userRepo.save(user);
        return true;
    }
    public boolean updateOTP(Long id,Integer otp) {
        User user = userRepo.findById(id).get();
        user.setOtp(otp);
        userRepo.save(user);;
        return true;
    }

    public Optional<User> getUserByEmail(String email) {
        return userRepo.findByEmail(email);
    }

//    public List<Booking> getBookingByUserByUserId(int user_id) {
//        return userRepo.findById(user_id).get().getBookings();
//    }
//public Integer getCountOfUser() {
//    return this.getUsersByRole("user").size();
//}

}
