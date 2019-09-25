package co.edu.icesi.miniproyecto.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
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
	public void addServicio(Tmio1Servicio servicio) throws Exception {
		if (servicio == null) {
			throw new NullPointerException("El servicio no puede ser nulo");
		}
		
		if (servicio.getTmio1Bus() == null) {
			throw new Exception("El bus debe de estar registrado");
		}
		
		Tmio1Bus bus = busesRepository.getBus(servicio.getTmio1Bus().getId()) ;
		if (bus == null) {
			throw new Exception("El bus debe de estar registrado");
		}
		
		if (servicio.getTmio1Conductore() == null) {
			throw new Exception("El conductor debe de estar registrado");
		}
		Tmio1Conductore conductor = conductoresRepository.getConductor(servicio.getTmio1Conductore().getCedula());
		
		if (conductor == null) {
			throw new Exception("El conductor debe de estar registrado");
		}
		
		if (servicio.getTmio1Ruta() == null) {
			throw new Exception("La ruta debe de estar registrada");
		}
		
		Tmio1Ruta ruta = rutasRepository.getRutas(servicio.getTmio1Ruta().getId());
		if (ruta == null) {
			throw new Exception("La ruta debe de estar registrada");
		}
		
		repository.addServicio(servicio);
	}

	@Override
	public Tmio1Servicio removeServicio(Tmio1Servicio servicio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setServicio(Tmio1Servicio servicio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tmio1Servicio getServicio(String id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tmio1Servicio> getAllServicios() {
		// TODO Auto-generated method stub
		return null;
	}


}
