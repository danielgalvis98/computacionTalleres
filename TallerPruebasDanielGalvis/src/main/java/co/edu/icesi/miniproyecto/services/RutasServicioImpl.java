package co.edu.icesi.miniproyecto.services;

import java.math.BigDecimal;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.repository.RutasRepository;

@Service
public class RutasServicioImpl implements RutasServicio{

	private RutasRepository repository;
	
	@Autowired
	public RutasServicioImpl(RutasRepository repo) {
		repository = repo;
	}
	
	@Override
	@Transactional
	public void addRuta(Tmio1Ruta ruta){
		if (ruta == null) {
			throw new NullPointerException("La ruta no puede ser nula");
		}
		if (ruta.getDiaFin().compareTo(ruta.getDiaInicio()) < 0)
			throw new RuntimeException("El día de inicio debe ser menor al día de fin");
		
		if (ruta.getHoraFin().compareTo(ruta.getHoraInicio()) < 0) {
			throw new RuntimeException("La hora de inicio ser menor la hora de fin");
		}
		repository.save(ruta);
	}

	@Override
	public Tmio1Ruta deleteRuta(Tmio1Ruta ruta) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRuta(Tmio1Ruta ruta) {
		// TODO Auto-generated method stub
		
	}

	@Override
	@Transactional
	public Tmio1Ruta getRuta(int id) {
		return repository.findById(id);
	}

	@Override
	@Transactional
	public Iterable<Tmio1Ruta> getAllRutas() {
		// TODO Auto-generated method stub
		return repository.findAll();
	}

}
