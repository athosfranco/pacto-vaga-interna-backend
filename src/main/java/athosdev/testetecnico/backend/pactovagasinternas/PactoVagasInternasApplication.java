package athosdev.testetecnico.backend.pactovagasinternas;

import athosdev.testetecnico.backend.pactovagasinternas.model.UserRole;
import athosdev.testetecnico.backend.pactovagasinternas.model.User;
import athosdev.testetecnico.backend.pactovagasinternas.repository.RoleRepository;
import athosdev.testetecnico.backend.pactovagasinternas.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashSet;
import java.util.Set;

@SpringBootApplication
public class PactoVagasInternasApplication {

	public static void main(String[] args) {
		SpringApplication.run(PactoVagasInternasApplication.class, args);
	}


	@Bean
	CommandLineRunner run(RoleRepository roleRep, UserRepository userRep, PasswordEncoder pwdEncoder) {
		return args -> {
			if(roleRep.findByAuthority("ADMIN").isPresent()) return;

			UserRole adminRole = roleRep.save(new UserRole("ADMIN"));
			roleRep.save(new UserRole("USER"));

			Set<UserRole> roles = new HashSet<>();

			roles.add(adminRole);

			User admin = new User(1, "admin", pwdEncoder.encode("123"), roles);

			userRep.save(admin);
		};
	}
}
