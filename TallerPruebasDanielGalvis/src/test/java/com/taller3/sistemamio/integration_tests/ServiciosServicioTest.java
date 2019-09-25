package com.taller3.sistemamio.integration_tests;

import static org.junit.Assert.assertNotNull;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.repository.BusesRepository;
import co.edu.icesi.miniproyecto.repository.BusesRepositoryImpl;
import co.edu.icesi.miniproyecto.repository.ConductoresRepository;
import co.edu.icesi.miniproyecto.repository.ConductoresRepositoryImpl;
import co.edu.icesi.miniproyecto.repository.RutasRepository;
import co.edu.icesi.miniproyecto.repository.RutasRepositoryImpl;
import co.edu.icesi.miniproyecto.repository.ServiciosRepository;
import co.edu.icesi.miniproyecto.repository.ServiciosRepositoryImpl;
import co.edu.icesi.miniproyecto.services.ServiciosServicioImpl;

public class ServiciosServicioTest{

	private ServiciosRepository repository;
	
	private RutasRepository rutasRepository;
	
	private ConductoresRepository conductoresRepo;
	
	private BusesRepository busesRepo;
	
	private ServiciosServicioImpl service;
	
	private Tmio1Bus bus1;
	private Tmio1Bus bus2;
	
	private Tmio1Conductore conductore1;
	private Tmio1Conductore conductore2;
	
	private Tmio1Ruta ruta1;
	private Tmio1Ruta ruta2;
	
	private Tmio1ServicioPK servicioPk1;
	
	private void setupBuses() {
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
	
	private void setupConductores () throws ParseException {
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
	
	private void setupRutas() {
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
	
	private void setupRelations() {
		repository = new ServiciosRepositoryImpl();
		rutasRepository = new RutasRepositoryImpl();
		conductoresRepo = new ConductoresRepositoryImpl();
		busesRepo = new BusesRepositoryImpl();
		service = new ServiciosServicioImpl(repository, busesRepo, conductoresRepo, rutasRepository);
	}
	
	private void setupPks() throws ParseException {
		servicioPk1 = new Tmio1ServicioPK();
		servicioPk1.setFechaInicio((new SimpleDateFormat("MM/dd/yyyy")).parse("10/22/2019"));
		servicioPk1.setFechaFin((new SimpleDateFormat("MM/dd/yyyy")).parse("12/22/2019"));
	}
	
	private void addToRepository() {
		rutasRepository.addRuta(ruta1);
		conductoresRepo.addConductor(conductore1);
		busesRepo.addBus(bus1);
	}
	
	@BeforeMethod(alwaysRun = true)
	public void init() throws ParseException {
		setupBuses();
		setupConductores();
		setupRelations();
		setupRutas();
		setupPks();
		addToRepository();
	}
	
	@Test
	public void testAddServiceCorect() throws Exception {
		
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setNewId("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(ruta1);
		serv.setId(servicioPk1);
		service.addServicio(serv);
		
		Tmio1Servicio added = repository.getServicio("0");
		assertNotNull(added);
		assertEquals(added, serv);
	}
	
	@Test(expectedExceptions = Exception.class,
			expectedExceptionsMessageRegExp = "El bus debe de estar registrado")
	public void testAddServiceNullBus() throws Exception{
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setNewId("0");
		serv.setTmio1Bus(bus2);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(ruta1);
		serv.setId(servicioPk1);
		service.addServicio(serv);
	}
	
	@Test(expectedExceptions = Exception.class,
			expectedExceptionsMessageRegExp = "El conductor debe de estar registrado")
	public void testAddServiceNullConductor() throws Exception{
		
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setNewId("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(conductore2);
		serv.setTmio1Ruta(ruta1);
		serv.setId(servicioPk1);
		service.addServicio(serv);
	}
	
	@Test(expectedExceptions = Exception.class,
			expectedExceptionsMessageRegExp = "La ruta debe de estar registrada")
	public void testAddServiceNullRuta() throws Exception{
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setNewId("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(ruta2);
		serv.setId(servicioPk1);
		service.addServicio(serv);
	}
	
}
