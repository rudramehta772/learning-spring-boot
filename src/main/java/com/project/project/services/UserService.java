// package com.project.project.services;

// import java.util.Optional;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
// import org.springframework.security.crypto.password.PasswordEncoder;
// import org.springframework.stereotype.Service;

// import com.project.project.dao.UserRepository;
// import com.project.project.models.Users;

// @Service
// public class UserService {

//     @Autowired
//     private UserRepository userRepository;

//     private PasswordEncoder passwordEncoder;

//     public Optional<Users> authenticateUser(String username, String rawPassword)
//     {
//         Optional<Users> optionalUser = userRepository.findByUsername(username);

//         if (optionalUser.isPresent()) {
//             Users user = optionalUser.get();
            
//             if (passwordEncoder.matches(rawPassword, user.getPassword())) {
//                 return Optional.of(user);
//             }
//         }
//         return Optional.empty();
//     }

// }
