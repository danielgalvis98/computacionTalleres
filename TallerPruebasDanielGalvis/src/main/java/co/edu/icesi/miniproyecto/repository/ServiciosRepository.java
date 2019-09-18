package co.edu.icesi.miniproyecto.repository;

import java.util.List;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;

public interface ServiciosRepository {

	public void addServicio (Tmio1Servicio servicio);
	
	public Tmio1Servicio removeServicio (Tmio1Servicio servicio);
	
	public void setServicio (Tmio1Servicio servicio);
	
	public Tmio1Servicio getServicio(String servicioId);
	
	public List<Tmio1Servicio> getAllServicios();
}
