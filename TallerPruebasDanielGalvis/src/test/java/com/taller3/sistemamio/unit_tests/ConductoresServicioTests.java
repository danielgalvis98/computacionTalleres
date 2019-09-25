package com.taller3.sistemamio.unit_tests;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.verifyZeroInteractions;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.repository.ConductoresRepository;
import co.edu.icesi.miniproyecto.services.ConductoresServicioImpl;

public class ConductoresServicioTests {
	
	@Mock
	private ConductoresRepository conductoresRepo;
	
	@InjectMocks
	private ConductoresServicioImpl service;
	
	@BeforeMethod(alwaysRun = true)
	public void initMock() {
		MockitoAnnotations.initMocks(this);
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
		verify(conductoresRepo).addConductor(conductor);
		
		verifyNoMoreInteractions(conductoresRepo);
	}
	
	@Test(expectedExceptions = Exception.class, 
			expectedExceptionsMessageRegExp = "Un conductor no puede ser contratado antes de su fecha de nacimiento")
	public void testAddConductorInconsistent () throws Exception {
		Tmio1Conductore conductor = new Tmio1Conductore();
		conductor.setCedula("1");
		conductor.setApellidos("a");
		conductor.setFechaNacimiento((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/2019"));
		conductor.setFechaContratacion((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/1980"));
		conductor.setNombre("Arroyo");
		
		service.addConductor(conductor);
		verifyZeroInteractions(conductoresRepo);
	}
	
	@Test(expectedExceptions = NullPointerException.class, 
			expectedExceptionsMessageRegExp = "El conductor no puede ser nulo")
	public void testAddConductorNull () throws Exception {
		Tmio1Conductore conductor = null;
		
		service.addConductor(conductor);
		verifyZeroInteractions(conductoresRepo);
	}
}
