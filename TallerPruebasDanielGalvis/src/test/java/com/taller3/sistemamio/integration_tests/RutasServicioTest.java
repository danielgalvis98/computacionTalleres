package com.taller3.sistemamio.integration_tests;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.repository.RutasRepository;
import co.edu.icesi.miniproyecto.repository.RutasRepositoryImpl;
import co.edu.icesi.miniproyecto.services.RutasServicioImpl;

public class RutasServicioTest {
	
	private RutasRepository repository;
	
	private RutasServicioImpl service;
	
	@BeforeMethod(alwaysRun = true)
	public void init() {
		repository = new RutasRepositoryImpl();
		service = new RutasServicioImpl(repository);
	}
	
	@Test
	public void testAddRutaCorrect() throws Exception {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setId(0);
		ruta.setActiva("Y");
		ruta.setDescripcion("test");
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(82800));
		ruta.setHoraInicio(new BigDecimal(18000));
		ruta.setNumero("T31");
		
		service.addRuta(ruta);
		assertNotNull(repository.getRutas(0));
		assertEquals(repository.getRutas(0), ruta);
	}
}
