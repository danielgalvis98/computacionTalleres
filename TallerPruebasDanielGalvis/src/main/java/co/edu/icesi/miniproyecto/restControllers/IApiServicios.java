package co.edu.icesi.miniproyecto.restControllers;

import java.time.LocalDate;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.icesi.miniproyecto.model.Tmio1Servicio;

public interface IApiServicios {

	public Iterable<Tmio1Servicio> getServicios();
	
	public void saveServicio(Tmio1Servicio service) throws Exception;
	
	public void removeServicio(Tmio1Servicio service);
	
	public Iterable<Tmio1Servicio> getServicesFiltered(LocalDate toCompare);
	
	public Tmio1Servicio getServicio(@PathVariable("idRuta") int idRuta, @PathVariable("cedulaConductor")
	String cedulaConductor, @PathVariable("idBus")int idBus, @PathVariable("fechaInicio") String fechaInicio,
	@PathVariable("fechaFin") String fechaFin);
	
	public void deleteServicio(@PathVariable("idRuta") int idRuta, @PathVariable("cedulaConductor")
	String cedulaConductor, @PathVariable("idBus")int idBus, @PathVariable("fechaInicio") String fechaInicio,
	@PathVariable("fechaFin") String fechaFin);
}
