package co.edu.icesi.miniproyecto;


import java.math.BigDecimal;
import java.time.LocalDate;

import javax.transaction.Transactional;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.thymeleaf.extras.java8time.dialect.Java8TimeDialect;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Sitio;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;
import co.edu.icesi.miniproyecto.model.Tmio1Usuario;
import co.edu.icesi.miniproyecto.model.UserType;
import co.edu.icesi.miniproyecto.repository.BusesRepository;
import co.edu.icesi.miniproyecto.repository.ConductoresRepository;
import co.edu.icesi.miniproyecto.repository.RutasRepository;
import co.edu.icesi.miniproyecto.repository.ServiciosRepository;
import co.edu.icesi.miniproyecto.repository.UserRepository;
import co.edu.icesi.miniproyecto.services.BusesServicio;
import co.edu.icesi.miniproyecto.services.ConductoresServicio;
import co.edu.icesi.miniproyecto.services.RutasServicio;
import co.edu.icesi.miniproyecto.services.SitiosRutaService;
import co.edu.icesi.miniproyecto.services.SitiosServicio;

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
	public CommandLineRunner runner(UserRepository userRepository, BusesServicio busRepo,
			ConductoresServicio conducRepo, RutasServicio rutasRepo, ServiciosRepository servRepo,
			SitiosServicio sitiosServ, SitiosRutaService sitioRutaServ) {
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
			
			Tmio1Bus bus = new Tmio1Bus();
			bus.setCapacidad(new BigDecimal(1000));
			bus.setMarca("Volks");
			bus.setModelo(new BigDecimal(2020));
			bus.setPlaca("123");
			bus.setTipo("Alim");
			busRepo.addBus(bus);
			
			Tmio1Conductore conduc = new Tmio1Conductore();
			conduc.setApellidos("dasda");
			conduc.setCedula("123123");
			conduc.setFechaContratacion(LocalDate.parse("2019-01-01"));
			conduc.setFechaNacimiento(LocalDate.parse("1998-06-03"));
			conduc.setNombre("asdasda");
			conducRepo.addConductor(conduc);
			
			Tmio1Ruta ruta = new Tmio1Ruta();
			ruta.setActiva("dasd");
			ruta.setDescripcion("dasda");
			ruta.setDiaFin(new BigDecimal(6));
			ruta.setDiaInicio(new BigDecimal(1));
			ruta.setHoraFin(new BigDecimal(1000));
			ruta.setHoraInicio(new BigDecimal(1));
			ruta.setNumero("dsadas");
			rutasRepo.addRuta(ruta);
			
			Tmio1Sitio sitio = new Tmio1Sitio();
			sitio.setDescripcion("dasdasd");
			sitio.setNombre("Universidades");
			sitiosServ.addSitio(sitio);
			
			Tmio1Sitio sitio2 = new Tmio1Sitio();
			sitio2.setDescripcion("dasdasd");
			sitio2.setNombre("Menga");
			sitiosServ.addSitio(sitio2);
			
			Tmio1SitiosRuta sitioRuta = new Tmio1SitiosRuta();
			
			
			
			Tmio1SitiosRutaPK pk = new Tmio1SitiosRutaPK();
			pk.setIdRuta(1);
			pk.setIdSitio(1);
			sitioRuta.setId(pk);
			sitioRutaServ.addSitioRuta(sitioRuta);
		};
	}
	

}
