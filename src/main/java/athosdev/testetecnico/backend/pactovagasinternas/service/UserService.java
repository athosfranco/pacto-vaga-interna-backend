package athosdev.testetecnico.backend.pactovagasinternas.service;

import athosdev.testetecnico.backend.pactovagasinternas.model.Skill;
import athosdev.testetecnico.backend.pactovagasinternas.model.User;
import athosdev.testetecnico.backend.pactovagasinternas.repository.SkillRepository;
import athosdev.testetecnico.backend.pactovagasinternas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private SkillRepository skillRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        System.out.println("In the UserDetailsService");

        return userRepository.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("Usuário não foi encontrado (Procurando por username)"));
    }

    public List<User> getUserList() {
        return userRepository.findAll();
    }

    public User getUserById(Integer userId) {
        // retornar DTO  e criar uma exception customizado
        return  userRepository.findById(userId).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não foi encontrado (Procurando por ID)"));
    }

}
