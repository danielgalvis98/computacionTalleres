package com.taller3.sistemamio.unit_tests;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.fail;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.repository.BusesRepository;
import co.edu.icesi.miniproyecto.repository.ConductoresRepository;
import co.edu.icesi.miniproyecto.repository.RutasRepository;
import co.edu.icesi.miniproyecto.repository.ServiciosRepository;
import co.edu.icesi.miniproyecto.services.ServiciosServicioImpl;

public class ServiciosServicioTest {

	@Mock
	private ServiciosRepository repository;
	
	@Mock
	private RutasRepository rutasRepository;
	
	@Mock
	private ConductoresRepository conductoresRepo;
	
	@Mock
	private BusesRepository busesRepo;
	
	@InjectMocks
	private ServiciosServicioImpl service;
	
	private Tmio1Bus bus1;
	private Tmio1Bus bus2;
	
	private Tmio1Conductore conductore1;
	private Tmio1Conductore conductore2;
	
	private Tmio1Ruta ruta1;
	private Tmio1Ruta ruta2;
	
	private Tmio1ServicioPK servicioPk1;
	private Tmio1ServicioPK servicioPk2;
	private Tmio1ServicioPK servicioPk3;
	
	@BeforeClass
	public void instantiateRelationships() throws ParseException {
		initializeBuses();
		initializeConductores();
		initializeRutas();
		initializePks();
	}
	
	public void initializeBuses () {
		bus1 = new Tmio1Bus();
		bus1.setId(0);
		bus1.setMarca("Volkswagen");
		bus1.setModelo(new BigDecimal(10));
		bus1.setPlaca("ABC");
		bus1.setTipo("T");
		
		bus2 = new Tmio1Bus();
		bus2.setId(1);
		bus2.setMarca("Volkswagen");
		bus2.setModelo(new BigDecimal(10));
		bus2.setPlaca("DEF");
		bus2.setTipo("P");
	}
	
	public void initializeConductores() throws ParseException {
		conductore1 = new Tmio1Conductore();
		conductore1.setCedula("1");
		conductore1.setApellidos("a");
		conductore1.setFechaNacimiento((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/1980"));
		conductore1.setFechaContratacion((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/2019"));
		conductore1.setNombre("Arroyo");
		
		conductore2 = new Tmio1Conductore();
		conductore2.setCedula("2");
		conductore2.setApellidos("a");
		conductore2.setFechaNacimiento((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/1980"));
		conductore2.setFechaContratacion((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/2019"));
		conductore2.setNombre("Arroyo");
	}
	
	public void initializeRutas () {
		ruta1 = new Tmio1Ruta();
		ruta1.setId(0);
		ruta1.setActiva("Y");
		ruta1.setDescripcion("test");
		ruta1.setDiaFin(new BigDecimal(5));
		ruta1.setDiaInicio(new BigDecimal(1));
		ruta1.setHoraFin(new BigDecimal(82800));
		ruta1.setHoraInicio(new BigDecimal(18000));
		ruta1.setNumero("T31");

		ruta2 = new Tmio1Ruta();
		ruta2.setId(1);
		ruta2.setActiva("Y");
		ruta2.setDescripcion("test");
		ruta2.setDiaFin(new BigDecimal(5));
		ruta2.setDiaInicio(new BigDecimal(1));
		ruta2.setHoraFin(new BigDecimal(82800));
		ruta2.setHoraInicio(new BigDecimal(18000));
		ruta2.setNumero("T31");
	}
	
	public void initializePks () throws ParseException {
		servicioPk1 = new Tmio1ServicioPK();
		servicioPk1.setFechaInicio((new SimpleDateFormat("MM/dd/yyyy")).parse("10/22/2019"));
		servicioPk1.setFechaFin((new SimpleDateFormat("MM/dd/yyyy")).parse("12/22/2019"));
		
		servicioPk2 = new Tmio1ServicioPK();
		servicioPk2.setFechaInicio((new SimpleDateFormat("MM/dd/yyyy")).parse("10/22/2019"));
		servicioPk2.setFechaFin((new SimpleDateFormat("MM/dd/yyyy")).parse("09/22/2019"));
		
		servicioPk3 = new Tmio1ServicioPK();
		servicioPk3.setFechaInicio((new SimpleDateFormat("MM/dd/yyyy")).parse("08/22/2019"));
		servicioPk3.setFechaFin((new SimpleDateFormat("MM/dd/yyyy")).parse("12/22/2019"));
		
	}
	
	@BeforeMethod(alwaysRun = true)
	public void initMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddServiceCorect() throws Exception {
		when(rutasRepository.getRutas(ruta1.getId())).thenReturn(ruta1);
		when(busesRepo.getBus(bus1.getId())).thenReturn(bus1);
		when(conductoresRepo.getConductor(conductore1.getCedula())).thenReturn(conductore1);
		
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setNewId("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(ruta1);
		serv.setId(servicioPk1);
		service.addServicio(serv);
		verify(repository).addServicio(serv);
		verify(rutasRepository).getRutas(ruta1.getId());
		verify(busesRepo).getBus(bus1.getId());
		verify(conductoresRepo).getConductor(conductore1.getCedula());
		
		verifyNoMoreInteractions(repository);
		verifyNoMoreInteractions(rutasRepository);
		verifyNoMoreInteractions(busesRepo);
		verifyNoMoreInteractions(conductoresRepo);
	}
	
	@Test
	public void testAddServiceNullBus(){
		when(rutasRepository.getRutas(ruta1.getId())).thenReturn(ruta1);
		when(busesRepo.getBus(bus2.getId())).thenReturn(null);
		when(conductoresRepo.getConductor(conductore1.getCedula())).thenReturn(conductore1);
		
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setNewId("0");
		serv.setTmio1Bus(bus2);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(ruta1);
		serv.setId(servicioPk1);
		try {
			service.addServicio(serv);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El bus debe de estar registrado");
			verify(busesRepo).getBus(bus2.getId());
			verify(conductoresRepo).getConductor(conductore1.getCedula());
			
			verifyNoMoreInteractions(repository);
			verifyNoMoreInteractions(rutasRepository);
			verifyNoMoreInteractions(busesRepo);
			verifyNoMoreInteractions(conductoresRepo);
		}
	}
	
	@Test
	public void testAddServiceNullConductor(){
		when(rutasRepository.getRutas(ruta1.getId())).thenReturn(ruta1);
		when(busesRepo.getBus(bus1.getId())).thenReturn(bus1);
		when(conductoresRepo.getConductor(conductore2.getCedula())).thenReturn(null);
		
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setNewId("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(conductore2);
		serv.setTmio1Ruta(ruta1);
		serv.setId(servicioPk1);
		try {
			service.addServicio(serv);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El conductor debe de estar registrado");
			verify(conductoresRepo).getConductor(conductore2.getCedula());
			
			verifyNoMoreInteractions(repository);
			verifyNoMoreInteractions(rutasRepository);
			verifyNoMoreInteractions(busesRepo);
			verifyNoMoreInteractions(conductoresRepo);
		}
	}
	
	@Test
	public void testAddServiceNullRuta(){
		when(rutasRepository.getRutas(ruta2.getId())).thenReturn(null);
		when(busesRepo.getBus(bus1.getId())).thenReturn(bus1);
		when(conductoresRepo.getConductor(conductore1.getCedula())).thenReturn(conductore1);
		
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setNewId("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(ruta2);
		serv.setId(servicioPk1);
		try {
			service.addServicio(serv);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "La ruta debe de estar registrada");
			verify(conductoresRepo).getConductor(conductore1.getCedula());
			verify(busesRepo).getBus(bus1.getId());
			verify(rutasRepository).getRutas(ruta2.getId());
			
			verifyNoMoreInteractions(repository);
			verifyNoMoreInteractions(rutasRepository);
			verifyNoMoreInteractions(busesRepo);
			verifyNoMoreInteractions(conductoresRepo);
		}
	}
	
	@Test(expectedExceptions = NullPointerException.class,
			expectedExceptionsMessageRegExp = "El servicio no puede ser nulo")
	public void testAddServiceNull() throws Exception {
		Tmio1Servicio serv = null;
		service.addServicio(serv);
	}
	
	@Test(expectedExceptions = Exception.class,
			expectedExceptionsMessageRegExp = "La fecha de fin del servicio debe de ser"
					+ " después de la fecha de inicio del servicio")
	public void testAddServiceInconsistentDates() throws Exception {
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setNewId("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(ruta1);
		serv.setId(servicioPk2);
		service.addServicio(serv);
	}
	
	@Test
	public void testAddServiceInconsistentConductor(){
		when(conductoresRepo.getConductor(conductore1.getCedula())).thenReturn(conductore1);
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setNewId("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(ruta1);
		serv.setId(servicioPk3);
		try {
			service.addServicio(serv);
			fail();
		} catch (Exception e) {
			assertEquals(e.getMessage(), "El conductor debe haber sido contratado antes de la fecha de inicio del servicio");
			
			verify(conductoresRepo).getConductor(conductore1.getCedula());
			verifyNoMoreInteractions(conductoresRepo);
			verifyNoMoreInteractions(repository);
		}
	}
}
