package co.edu.icesi.miniproyecto.services;

import java.util.List;
import java.util.Optional;

import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;

public interface ServiciosServicio {

	public void addServicio (Tmio1Servicio servicio) throws Exception;
	
	public Tmio1Servicio removeServicio (Tmio1Servicio servicio);
	
	public void setServicio (Tmio1Servicio servicio) throws Exception;
	
	public Tmio1Servicio getServicio (Tmio1ServicioPK id);
	
	public Iterable<Tmio1Servicio> getAllServicios();
}
