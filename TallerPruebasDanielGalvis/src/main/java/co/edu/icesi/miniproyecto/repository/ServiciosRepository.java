package co.edu.icesi.miniproyecto.repository;

import org.springframework.data.repository.CrudRepository;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;


public interface ServiciosRepository extends CrudRepository<Tmio1Servicio, Tmio1ServicioPK> {

}
