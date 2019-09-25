package com.taller3.sistemamio.integration_tests;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.text.SimpleDateFormat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.repository.ConductoresRepository;
import co.edu.icesi.miniproyecto.repository.ConductoresRepositoryImpl;
import co.edu.icesi.miniproyecto.services.ConductoresServicioImpl;

public class ConductoresServicioTests {

	private ConductoresRepository conductoresRepo;
	
	private ConductoresServicioImpl service;
	
	@BeforeMethod(alwaysRun = true)
	public void init() {
		conductoresRepo = new ConductoresRepositoryImpl();
		service = new ConductoresServicioImpl(conductoresRepo);
	}
	
	@Test
	public void testAddConductorCorrect() throws Exception {
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setCedula("1");
		conductor.setApellidos("a");
		conductor.setFechaNacimiento((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/1980"));
		conductor.setFechaContratacion((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/2019"));
		conductor.setNombre("Arroyo");
		
		service.addConductor(conductor);
		assertNotNull(conductoresRepo.getConductor("1"));
		assertEquals(conductoresRepo.getConductor("1"), conductor);
	}
}
