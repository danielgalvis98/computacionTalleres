package co.edu.icesi.miniproyecto.services;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.repository.BusesRepository;
import co.edu.icesi.miniproyecto.repository.ConductoresRepository;
import co.edu.icesi.miniproyecto.repository.RutasRepository;
import co.edu.icesi.miniproyecto.repository.ServiciosRepository;

@Service
public class ServiciosServicioImpl implements ServiciosServicio{

	private ServiciosRepository repository;
	
	private BusesRepository busesRepository;
	
	private ConductoresRepository conductoresRepository;
	
	private RutasRepository rutasRepository;
	
	@Autowired
	public ServiciosServicioImpl(ServiciosRepository repo, BusesRepository busRepo,
			ConductoresRepository conductoresRepo, RutasRepository rutasRepo) {
		repository = repo;
		busesRepository = busRepo;
		conductoresRepository = conductoresRepo;
		rutasRepository = rutasRepo;
	}
	
	@Override
	@Transactional
	public void addServicio(Tmio1Servicio servicio)  {
		if (servicio == null) {
			throw new NullPointerException("El servicio no puede ser nulo");
		}
		
		if (servicio.getTmio1Conductore() == null) {
			throw new RuntimeException("El conductor debe de estar registrado");
		}
		
		if (servicio.getId().getFechaFin().compareTo(servicio.getId().getFechaInicio()) < 0)
			throw new RuntimeException("La fecha de fin del servicio debe de ser "
					+ "después de la fecha de inicio del servicio");
		
		Tmio1Conductore conductor = conductoresRepository.findById(servicio.getTmio1Conductore().getCedula());
		
		if (conductor == null) {
			throw new RuntimeException("El conductor debe de estar registrado");
		}
		
		if (servicio.getId().getFechaInicio().compareTo(conductor.getFechaContratacion()) < 0) {
			System.out.println(servicio.getId().getFechaInicio().toString());
			throw new RuntimeException("El conductor debe haber sido contratado antes de la fecha de inicio del servicio");			
		}
		
		if (servicio.getTmio1Bus() == null) {
			throw new RuntimeException("El bus debe de estar registrado");
		}
		
		Tmio1Bus bus = busesRepository.findById(servicio.getTmio1Bus().getId()) ;
		if (bus == null) {
			throw new RuntimeException("El bus debe de estar registrado");
		}
		
		if (servicio.getTmio1Ruta() == null) {
			throw new RuntimeException("La ruta debe de estar registrada");
		}
		
		Tmio1Ruta ruta = rutasRepository.findById(servicio.getTmio1Ruta().getId());
		if (ruta == null) {
			throw new RuntimeException("La ruta debe de estar registrada");
		}
		
		Tmio1ServicioPK pk = servicio.getId();
		
		if (repository.findById(pk) != null)
			throw new RuntimeException("Ya existe un servicio con esa informaci�n");
		repository.save(servicio);
	}

	@Override
	@Transactional
	public Tmio1Servicio removeServicio(Tmio1Servicio servicio) {
		repository.delete(servicio);
		return servicio;
	}

	@Override
	@Transactional
	public void setServicio(Tmio1Servicio servicio) throws Exception {
		Tmio1Servicio serviceOpt= repository.findById(servicio.getId());
		if (serviceOpt == null)
			throw new Exception("No hay ningun servicio con ese id");
		addServicio(servicio);
		
	}

	@Override
	@Transactional
	public Tmio1Servicio getServicio(Tmio1ServicioPK id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Iterable<Tmio1Servicio> getAllServicios() {
		return repository.findAll();
	}


}
