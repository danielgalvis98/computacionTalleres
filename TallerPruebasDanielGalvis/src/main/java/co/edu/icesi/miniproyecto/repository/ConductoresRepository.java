package co.edu.icesi.miniproyecto.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.miniproyecto.dtos.ConducsWithServices;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

public interface ConductoresRepository {
	
	public void save (Tmio1Conductore conductor);
	public Tmio1Conductore findById(String cedula);
	public Iterable<Tmio1Conductore> findAll();
	public void update (Tmio1Conductore conductor);
	public void delete (Tmio1Conductore conductor);
	public List<Tmio1Conductore> findByName (String name);
	public List<Tmio1Conductore> findByApellidos (String apellidos);
	public List<ConducsWithServices> findByServiceDates(LocalDate date);

}
