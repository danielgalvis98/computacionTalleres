package co.edu.icesi.miniproyecto;


import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.miniproyecto.model.Tmio1Usuario;
import co.edu.icesi.miniproyecto.model.UserType;
import co.edu.icesi.miniproyecto.repository.BusesRepository;
import co.edu.icesi.miniproyecto.repository.ConductoresRepository;
import co.edu.icesi.miniproyecto.repository.RutasRepository;
import co.edu.icesi.miniproyecto.repository.ServiciosRepository;
import co.edu.icesi.miniproyecto.repository.UserRepository;

@SpringBootApplication
public class TallerPruebasDanielGalvisApplication {
	
	@Bean
	public Java8TimeDialect java8TimeDialect() {
		return new Java8TimeDialect();
	}

	public static void main(String[] args) {
		SpringApplication.run(TallerPruebasDanielGalvisApplication.class, args);
	}
	
	@Bean
	public CommandLineRunner runner(UserRepository userRepository, BusesRepository busRepo,
			ConductoresRepository conducRepo, RutasRepository rutasRepo, ServiciosRepository servRepo) {
		return (args) -> {
			BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
			
			Tmio1Usuario user = new Tmio1Usuario();
			user.setUsername("admin-test");
			user.setPassword(encoder.encode("123"));
			user.setType(UserType.admin);
			userRepository.save(user);
			
			Tmio1Usuario user2 = new Tmio1Usuario();
			user2.setUsername("operador-test");
			user2.setPassword(encoder.encode("123"));
			user2.setType(UserType.operador);
			userRepository.save(user2);
			
		};
	}
	

}
