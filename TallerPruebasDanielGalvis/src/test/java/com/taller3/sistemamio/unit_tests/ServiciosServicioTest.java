package com.taller3.sistemamio.unit_tests;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;

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
import co.edu.icesi.miniproyecto.repository.ServiciosRepository;
import co.edu.icesi.miniproyecto.services.ServiciosServicioImpl;

public class ServiciosServicioTest {

	@Mock
	private ServiciosRepository repository;
	
	@InjectMocks
	private ServiciosServicioImpl service;
	
	private Tmio1Bus bus1;
	private Tmio1Conductore conductore1;
	private Tmio1Ruta ruta1;
	
	@BeforeClass
	public void instantiateRelationships() {
		bus1 = new Tmio1Bus();
		conductore1 = new Tmio1Conductore();
		ruta1 = new Tmio1Ruta();
	}
	
	@BeforeMethod(alwaysRun = true)
	public void initMock() {
		MockitoAnnotations.initMocks(this);
	}
	
	@Test
	public void testAddServiceCorect() throws Exception {
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setNewId("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(ruta1);
		service.addServicio(serv);
		verify(repository).addServicio(serv);
		verifyNoMoreInteractions(repository);
	}
	
	@Test(expectedExceptions = Exception.class,
			expectedExceptionsMessageRegExp = "El bus debe de estar registrado")
	public void testAddServiceNullBus() throws Exception {
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setNewId("0");
		serv.setTmio1Bus(null);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(ruta1);
		service.addServicio(serv);
	}
	
	@Test(expectedExceptions = Exception.class,
			expectedExceptionsMessageRegExp = "El conductor debe de estar registrado")
	public void testAddServiceNullConductor() throws Exception {
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setNewId("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(null);
		serv.setTmio1Ruta(ruta1);
		service.addServicio(serv);
	}
	
	@Test(expectedExceptions = Exception.class,
			expectedExceptionsMessageRegExp = "La ruta debe de estar registrada")
	public void testAddServiceNullRuta() throws Exception {
		Tmio1Servicio serv = new Tmio1Servicio();
		serv.setNewId("0");
		serv.setTmio1Bus(bus1);
		serv.setTmio1Conductore(conductore1);
		serv.setTmio1Ruta(null);
		service.addServicio(serv);
	}
	
	@Test(expectedExceptions = NullPointerException.class,
			expectedExceptionsMessageRegExp = "El servicio no puede ser nulo")
	public void testAddServiceNull() throws Exception {
		Tmio1Servicio serv = null;
		service.addServicio(serv);
	}
}
