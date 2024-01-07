package athosdev.testetecnico.backend.pactovagasinternas.controller;

import athosdev.testetecnico.backend.pactovagasinternas.dto.ErrorResponseDTO;
import athosdev.testetecnico.backend.pactovagasinternas.dto.LoginResponseDTO;
import athosdev.testetecnico.backend.pactovagasinternas.dto.LoginRequestDTO;
import athosdev.testetecnico.backend.pactovagasinternas.dto.RegisterUserDTO;
import athosdev.testetecnico.backend.pactovagasinternas.model.User;
import athosdev.testetecnico.backend.pactovagasinternas.service.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.http.HttpStatus;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthenticationController {

    @Autowired
    private AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterUserDTO body) {
        try {

            User user = authenticationService.registerUser(body.getUsername(), body.getPassword());
            return ResponseEntity.ok(user);

        } catch (ResponseStatusException e) {

            ErrorResponseDTO errorResponse = new ErrorResponseDTO(e.getReason(), (HttpStatus) e.getStatusCode());
            return ResponseEntity.status(e.getStatusCode()).body(errorResponse);

        } catch (Exception e) {

            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody LoginRequestDTO body) {
        try {
            LoginResponseDTO response = authenticationService.loginUser(body.getUsername(), body.getPassword());
            return ResponseEntity.ok(response);
        } catch (ResponseStatusException e) {
            ErrorResponseDTO errorResponse = new ErrorResponseDTO(e.getReason(), (HttpStatus) e.getStatusCode());
            return ResponseEntity.status(e.getStatusCode()).body(errorResponse);
        }
    }



}
