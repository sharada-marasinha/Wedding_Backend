//package edu.lmu.service.impl;
//
//import edu.lmu.dto.CoupleSignUpRequest;
//import edu.lmu.dto.GuestSignUpRequest;
//import edu.lmu.dto.PlannerSignUpRequest;
//import edu.lmu.dto.VendorSignUpRequest;
//import edu.lmu.entity.User;
//import edu.lmu.entity.UserPrincipal;
//import edu.lmu.repository.UserRepository;
//import edu.lmu.service.UserService;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import java.util.List;
//import java.util.Optional;
//
//@Service
//@RequiredArgsConstructor
//public class UserServiceImpl implements UserService {
//
//    private final UserRepository userRepository;
//
//    public List<User> getAllUsers() {
//        return userRepository.findAll();
//    }
//
//    public Optional<User> getUserById(Integer id) {
//        return userRepository.findById(id);
//    }
//
//    public User createUser(User user) {
//        // You should add password encoding logic here before saving
//        return userRepository.save(user);
//    }
//
//    public User updateUser(Integer id, User updatedUser) {
//        Optional<User> existingUser = userRepository.findById(id);
//        if (existingUser.isPresent()) {
//            updatedUser.setId(Long.valueOf(id));
//            // Consider how you want to handle password updates
//            updatedUser.setPassword(existingUser.get().getPassword()); // Keeping existing for now
//            return userRepository.save(updatedUser);
//        }
//        return null; // Or throw an exception
//    }
//
//    public void deleteUser(Integer id) {
//        userRepository.deleteById(id);
//    }
//
//    public Optional<User> findByUsername(String username) {
//        return userRepository.findByUsername(username);
//    }
//
//    public Optional<User> findByEmail(String email) {
//        return userRepository.findByEmail(email);
//    }
//
//    public boolean existsByUsername(String username) {
//        return userRepository.existsByUsername(username);
//    }
//
//    public boolean existsByEmail(String email) {
//        return userRepository.existsByEmail(email);
//    }
//
//    @Override
//    public User registerCouple(CoupleSignUpRequest signUpRequest) {
//        return null;
//    }
//
//    @Override
//    public User registerPlanner(PlannerSignUpRequest signUpRequest) {
//        return null;
//    }
//
//    @Override
//    public User registerVendor(VendorSignUpRequest signUpRequest) {
//        return null;
//    }
//
//    @Override
//    public User registerGuest(GuestSignUpRequest signUpRequest) {
//        return null;
//    }
//
//    @Override
//    public UserPrincipal loadUserByUsername(String email) {
//        return null;
//    }
//
//    @Override
//    public User getUserById(Long id) {
//        return null;
//    }
//}