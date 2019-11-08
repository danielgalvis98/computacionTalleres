package co.edu.icesi.miniproyecto.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;


public interface BusesRepository {
	public void save(Tmio1Bus bus);
	public Tmio1Bus findByPlaca(String placa);
	public Tmio1Bus findById(int id);
	public List<Tmio1Bus> findByMarca (String marca);
	public List<Tmio1Bus> findByModelo (BigDecimal modelo);
	public void update(Tmio1Bus bus);
	public void delete(Tmio1Bus bus);
	public Iterable<Tmio1Bus> findAll();
	

}
