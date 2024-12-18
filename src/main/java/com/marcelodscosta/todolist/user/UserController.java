package com.marcelodscosta.todolist.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import at.favre.lib.crypto.bcrypt.BCrypt;

@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    private IUserRepository userRepository;

    @PostMapping("/")
    public ResponseEntity create(@RequestBody UserModel userModel) {
        var user = this.userRepository.findByUsername(userModel.getUsername());

        if (user != null) {
            System.out.printf("User %s already exists!%n", user.getUsername());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("User " + user.getUsername() + " already exists!");
        }

        var passwordHash = BCrypt.withDefaults().hashToString(12, userModel.getPassword().toCharArray()) ;

        userModel.setPassword(passwordHash);

        var userCreated = this.userRepository.save(userModel);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

}
