package co.edu.icesi.miniproyecto.services;

import java.util.Date;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.repository.ConductoresRepository;

@Service
public class ConductoresServicioImpl implements ConductoresServicio{

	private ConductoresRepository repository;
	
	@Autowired
	public ConductoresServicioImpl(ConductoresRepository repo) {
		repository = repo;
	}
	
	@Override
	@Transactional
	public void addConductor(Tmio1Conductore conductor) throws Exception {
		if (conductor == null) {
			throw new NullPointerException("El conductor no puede ser nulo");
		}
		if (conductor.getFechaContratacion().compareTo(conductor.getFechaNacimiento()) <= 0)
			throw new Exception("Un conductor no puede ser contratado antes de su fecha de nacimiento");
		
		repository.save(conductor);
		
		
	}

	@Override
	public Tmio1Conductore removeConductor(Tmio1Conductore conductor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConductor(Tmio1Conductore conductor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public Tmio1Conductore getConductor(String cedula) {
		return repository.findById(cedula);
	}

	@Override
	@Transactional
	public Iterable<Tmio1Conductore> getAllConductores() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}


}
