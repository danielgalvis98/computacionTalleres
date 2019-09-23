package com.taller3.sistemamio.unit_tests;

import org.mockito.InjectMocks;
import org.mockito.Mock;

import co.edu.icesi.miniproyecto.repository.RutasRepository;
import co.edu.icesi.miniproyecto.services.RutasServicioImpl;

public class RutasServicioTest {
	
	@Mock
	private RutasRepository repository;
	
	@InjectMocks
	private RutasServicioImpl service;

}
