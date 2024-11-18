package com.project.project.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.project.dao.UserRepository;
import com.project.project.models.Users;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public Users authenticateUser(String username, String rawPassword)
    {
        Optional<Users> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            
            if (passwordEncoder.matches(rawPassword, user.getPassword())) {
                return Optional.of(user).get();
            }
        }
        return null;
    }

    public Users encryptPassword(Users user){
        user.setPassword(this.passwordEncoder.encode(user.getPassword()));
        return user;
    }

    public Users saveUser(Users user){
        user = encryptPassword(user);
        Users saved_user = this.userRepository.save(user);
        return saved_user;   
    }

    public Users getUser(Long id){
        Optional<Users> user = this.userRepository.findById(id);
        return user.orElse(null);
    }

    public Users updateUser(Long id, Users user){
        Users old_user = this.userRepository.findById(id).get();
        
        if (user.getUsername() != null) {
            old_user.setUsername(user.getUsername());
        }

        if (user.getRole() != null) {
            old_user.setRole(user.getRole());
        }
        
        if (user.getPassword() != null){
            user = encryptPassword(user);
            old_user.setPassword(user.getPassword());
        }

        old_user.setIs_active(user.isIs_active());
        old_user.setIs_deleted(user.isIs_deleted());
        
        return userRepository.save(old_user);
    }

}