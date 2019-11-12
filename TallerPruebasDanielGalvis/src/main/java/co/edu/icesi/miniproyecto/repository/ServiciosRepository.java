package co.edu.icesi.miniproyecto.repository;

import org.springframework.data.repository.CrudRepository;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;


public interface ServiciosRepository{
	public void save (Tmio1Servicio serv);
	public Tmio1Servicio findById (Tmio1ServicioPK id);
	public Iterable<Tmio1Servicio> findAll();
	public void delete (Tmio1Servicio serv);
}
