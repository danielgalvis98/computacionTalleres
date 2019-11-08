package co.edu.icesi.miniproyecto.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;

public interface RutasRepository {

	public void save (Tmio1Ruta ruta);
	public Tmio1Ruta findById(int id);
	public Iterable<Tmio1Ruta> findAll();
	public void update (Tmio1Ruta ruta);
	public void delete (Tmio1Ruta ruta);
	public List<Tmio1Ruta> findByHours(BigDecimal horaInicio, BigDecimal horaFin);
	public List<Tmio1Ruta> findByDates(BigDecimal diaInicio, BigDecimal diafin);
}
