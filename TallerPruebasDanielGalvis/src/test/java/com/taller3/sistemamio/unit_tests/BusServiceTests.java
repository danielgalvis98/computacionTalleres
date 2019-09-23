package com.taller3.sistemamio.unit_tests;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.math.BigDecimal;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.repository.BusesRepository;
import co.edu.icesi.miniproyecto.services.BusesServicioImpl;

public class BusServiceTests {
	
	@Mock
	private BusesRepository busRepo;
	
	@InjectMocks
	private BusesServicioImpl busService;
	
	@BeforeMethod(alwaysRun = true)
	public void initMock() {
		MockitoAnnotations.initMocks(this);
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
		verify(busRepo).addBus(bus);
		
		verifyNoMoreInteractions(busRepo);
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
		verify(busRepo).addBus(bus);
		
		verifyNoMoreInteractions(busRepo);
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
		verify(busRepo).addBus(bus);
		
		verifyNoMoreInteractions(busRepo);
	}
	 
	@Test(expectedExceptions = NullPointerException.class, 
			expectedExceptionsMessageRegExp = "El bus no puede ser nulo")
	public void testAddNullBuss() {
		Tmio1Bus bus = null;
		busService.addBus(bus);
		verifyZeroInteractions(busRepo);
	}

}
