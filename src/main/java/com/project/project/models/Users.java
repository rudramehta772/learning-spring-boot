package com.project.project.models;



import com.project.project.utils.models.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;


@Entity
public class Users extends BaseEntity {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private boolean is_active = false;
    private boolean is_deleted = false;

    @Enumerated(EnumType.STRING)
    private UserRole role;

    

    
    public Users(Long id, String username, String password, boolean is_active, boolean is_deleted,
            String passwordEncoder, UserRole role) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.is_active = is_active;
        this.is_deleted = is_deleted;
        this.password = password;
        this.role = role;
    }

    public Users() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isIs_active() {
        return is_active;
    }

    public void setIs_active(boolean is_active) {
        this.is_active = is_active;
    }

    public boolean isIs_deleted() {
        return is_deleted;
    }

    public void setIs_deleted(boolean is_deleted) {
        this.is_deleted = is_deleted;
    }

    public UserRole getRole() {
        return role;
    }

    public void setRole(UserRole role) {
        this.role = role;
    }
    
    // private transient PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    // @PrePersist
    // public void encodePassword(){
    //     if (password != null && !password.isEmpty()){
    //         this.password = passwordEncoder.encode(this.password);
    //     } 
    // }
    
}


//Login example code => 

// @RestController
// @RequestMapping("/auth")
// public class AuthController {

//     @Autowired
//     private UserService userService;

//     @PostMapping("/login")
//     public ResponseEntity<String> login(@RequestParam String username, @RequestParam String password) {
//         Optional<Users> user = userService.authenticateUser(username, password);

//         if (user.isPresent()) {
//             return ResponseEntity.ok("Login successful");
//         } else {
//             return ResponseEntity.status(401).body("Invalid credentials");
//         }
//     }
// }



//Registration example code => 
// public Users registerUser(Users user) {
//     user.setPassword(passwordEncoder.encode(user.getPassword())); // Encode password before saving
//     return userRepository.save(user);
// }
