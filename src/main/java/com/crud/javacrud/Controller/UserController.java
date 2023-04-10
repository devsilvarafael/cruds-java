package com.crud.javacrud.Controller;

import com.crud.javacrud.Model.User;
import com.crud.javacrud.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    public UserRepository userRepository;

    @GetMapping
    public ResponseEntity<?> getAllUsers() {
        List<User> users = userRepository.findAll();

        return ResponseEntity.ok(users);
    }

    @GetMapping("/{id}") // Rota "GET" -> Consultar usuário pelo seu ID.
    public ResponseEntity<?> getUser(@PathVariable Long id) {
        Optional<User> userExists = userRepository.findById(id);

        if(userExists.isPresent()) {
            return ResponseEntity.ok(userExists);
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        User newUser = userRepository.save(user);

        return ResponseEntity.status(HttpStatus.CREATED).body(newUser); // Retorna HTTP Status 201 (Created) e insere na API o novo dado.
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Long id, User user) {
        Optional<User> userExists = userRepository.findById(id);

        if(userExists.isPresent()) {
            User updatedUser = userExists.get();

            updatedUser.setName(user.getName());
            updatedUser.setActive(user.getActive());

            User savedUser = userRepository.save(updatedUser);

            return ResponseEntity.ok(savedUser);
        }

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
