package athosdev.testetecnico.backend.pactovagasinternas.configuration;

import athosdev.testetecnico.backend.pactovagasinternas.model.UserRole;
import athosdev.testetecnico.backend.pactovagasinternas.model.User;
import athosdev.testetecnico.backend.pactovagasinternas.repository.RoleRepository;
import athosdev.testetecnico.backend.pactovagasinternas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class AppStartupRunner implements ApplicationRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {
        initializeRolesAndAdmin();
    }

    private void initializeRolesAndAdmin() {
        if (roleRepository.findByAuthority("ADMIN").isPresent()) {
            return; //RETORNA VAZIO SE JA HOUVER ADM
        }

        UserRole adminRole = roleRepository.save(new UserRole("ADMIN"));
        roleRepository.save(new UserRole("USER"));

        Set<UserRole> roles = new HashSet<>();
        roles.add(adminRole);

        //INICIALIZA USER ADMIN SE N HOUVER
        User admin = new User();

        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("123"));
        admin.setAuthorities(roles);
        admin.setFirstName("Administrador");

        userRepository.save(admin);
    }


}
