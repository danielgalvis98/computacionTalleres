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

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.repository.RutasRepository;
import co.edu.icesi.miniproyecto.services.RutasServicioImpl;

public class RutasServicioTest {
	
	@Mock
	private RutasRepository repository;
	
	@InjectMocks
	private RutasServicioImpl service;
	
	@BeforeMethod(alwaysRun = true)
	public void initMock() {
		MockitoAnnotations.initMocks(this);
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
		verify(repository).addRuta(ruta);
		verifyNoMoreInteractions(repository);
	}
	
	@Test(expectedExceptions = Exception.class,
			expectedExceptionsMessageRegExp = "El día de inicio debe ser menor al día de fin")
	public void testAddRutaFechasInconsistentes() throws Exception {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setId(1);
		ruta.setActiva("Y");
		ruta.setDescripcion("test");
		ruta.setDiaFin(new BigDecimal(1));
		ruta.setDiaInicio(new BigDecimal(5));
		ruta.setHoraFin(new BigDecimal(82800));
		ruta.setHoraInicio(new BigDecimal(18000));
		ruta.setNumero("T31");
		
		service.addRuta(ruta);
		verifyZeroInteractions(repository);
	}
	
	@Test(expectedExceptions = Exception.class, 
			expectedExceptionsMessageRegExp = "La hora de inicio ser menor la hora de fin")
	public void testAddRutaHorasInconsistentes() throws Exception {
		Tmio1Ruta ruta = new Tmio1Ruta();
		ruta.setId(2);
		ruta.setActiva("Y");
		ruta.setDescripcion("test");
		ruta.setDiaFin(new BigDecimal(5));
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(18000));
		ruta.setHoraInicio(new BigDecimal(82800));
		ruta.setNumero("T31");
		
		service.addRuta(ruta);
		verifyZeroInteractions(repository);
	}
	
	@Test(expectedExceptions = NullPointerException.class,
			expectedExceptionsMessageRegExp = "La ruta no puede ser nula")
	public void testAddRutaNull() throws Exception {
		Tmio1Ruta ruta = null;
		
		service.addRuta(ruta);
		verifyZeroInteractions(repository);
	}

}
