package athosdev.testetecnico.backend.pactovagasinternas.service;

import athosdev.testetecnico.backend.pactovagasinternas.dto.LoginResponseDTO;
import athosdev.testetecnico.backend.pactovagasinternas.model.UserRole;
import athosdev.testetecnico.backend.pactovagasinternas.model.User;
import athosdev.testetecnico.backend.pactovagasinternas.repository.RoleRepository;
import athosdev.testetecnico.backend.pactovagasinternas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@Transactional
public class AuthenticationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    private String badCredentialsMessage = "Usuário ou senha inválidos";

    public User registerUser(String username, String password) {
        // VERIFICA SE USER JA EXISTE
        if (userRepository.findUserByUsername(username).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário '" + username + "' já existe");
        }

        String encodedPassword = passwordEncoder.encode(password);

        UserRole userRole = roleRepository.findByAuthority("USER").orElseThrow(() -> new RuntimeException("Autorização não encontrada"));

        Set<UserRole> authorities = new HashSet<>();
        authorities.add(userRole);

        User newUser = new User();

        newUser.setUsername(username);
        newUser.setPassword(encodedPassword);
        newUser.setAuthorities(authorities);

        return userRepository.save(newUser);
    }

    public LoginResponseDTO loginUser(String username, String password) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password)
            );

            String token = tokenService.generateJwt(authentication);

            Optional<User> authenticatedUser = userRepository.findUserByUsername(username);

            return new LoginResponseDTO(authenticatedUser, token);

        } catch (AuthenticationException e) {
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, badCredentialsMessage, e);
        }
    }

}
