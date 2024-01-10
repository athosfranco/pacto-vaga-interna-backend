package athosdev.testetecnico.backend.pactovagasinternas.controller;

import athosdev.testetecnico.backend.pactovagasinternas.dto.ErrorResponseDTO;
import athosdev.testetecnico.backend.pactovagasinternas.model.User;
import athosdev.testetecnico.backend.pactovagasinternas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin("*")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getUserList();
    }

    @GetMapping("/{userId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer userId) {
        return ResponseEntity.ok(userService.getUserById(userId));
    }
}
