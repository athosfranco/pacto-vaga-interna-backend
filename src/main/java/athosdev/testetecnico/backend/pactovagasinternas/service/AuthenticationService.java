package athosdev.testetecnico.backend.pactovagasinternas.service;

import athosdev.testetecnico.backend.pactovagasinternas.dto.LoginResponseDTO;
import athosdev.testetecnico.backend.pactovagasinternas.dto.RegisterUserDTO;
import athosdev.testetecnico.backend.pactovagasinternas.model.UserRole;
import athosdev.testetecnico.backend.pactovagasinternas.model.User;
import athosdev.testetecnico.backend.pactovagasinternas.repository.RoleRepository;
import athosdev.testetecnico.backend.pactovagasinternas.repository.UserRepository;
import athosdev.testetecnico.backend.pactovagasinternas.utils.PasswordValidator;
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

    public User registerUser(RegisterUserDTO user) {
        // VERIFICA SE USER JA EXISTE
        if (userRepository.findUserByUsername(user.getUsername()).isPresent()) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "O usuário '" + user.getUsername() + "' já existe.");
        }

        //VALIDA A SENHA
        String encodedPassword;
        if (PasswordValidator.isValid(user.getPassword())) {
            encodedPassword = passwordEncoder.encode(user.getPassword());
        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "A senha precisa ter pelo menos 8 caracteres, uma letra e um número.");
        }

        UserRole userRole = roleRepository.findByAuthority("USER").orElseThrow(() -> new RuntimeException("Autorização 'USER' não encontrada no banco de dados."));

        Set<UserRole> authorities = new HashSet<>();

        authorities.add(userRole);

        User newUser = new User();

        newUser.setUsername(user.getUsername());
        newUser.setPassword(encodedPassword);
        newUser.setFirstName(user.getFirstName());
        newUser.setLastName(user.getLastName());
        newUser.setGender(user.getGender());
        newUser.setHireDate(user.getHireDate());
        newUser.setCurrentJob(user.getCurrentJob());
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
