package com.taller3.sistemamio.integration_tests;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.repository.BusesRepository;
import co.edu.icesi.miniproyecto.repository.BusesRepositoryImpl;
import co.edu.icesi.miniproyecto.services.BusesServicioImpl;

public class BusServiceTests {

	private BusesRepository busRepo;
	
	private BusesServicioImpl busService;
	
	@BeforeMethod(alwaysRun = true)
	public void init() {
		busRepo = new BusesRepositoryImpl();
		busService = new BusesServicioImpl(busRepo);
	}
	
	@Test
	public void testAddBusT () {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setId(0);
		bus.setMarca("Volkswagen");
		bus.setModelo(new BigDecimal(10));
		bus.setPlaca("ABC");
		bus.setTipo("T");
		
		busService.addBus(bus);
		
		assertNotNull(busRepo.getBus(0));
		assertEquals(busRepo.getBus(0), bus);
	}
	
	@Test
	public void testAddBusP () {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setId(1);
		bus.setMarca("Volkswagen");
		bus.setModelo(new BigDecimal(10));
		bus.setPlaca("DEF");
		bus.setTipo("P");
		
		busService.addBus(bus);
		assertNotNull(busRepo.getBus(1));
		assertEquals(busRepo.getBus(1), bus);
	}
	
	@Test
	public void testAddBusA () {
		Tmio1Bus bus = new Tmio1Bus();
		bus.setId(2);
		bus.setMarca("Volkswagen");
		bus.setModelo(new BigDecimal(10));
		bus.setPlaca("GHI");
		bus.setTipo("A");
		
		busService.addBus(bus);
		assertNotNull(busRepo.getBus(2));
		assertEquals(busRepo.getBus(2), bus);
	}
}
