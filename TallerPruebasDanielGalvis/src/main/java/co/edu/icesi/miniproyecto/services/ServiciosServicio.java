package co.edu.icesi.miniproyecto.services;

import java.util.List;

import co.edu.icesi.miniproyecto.model.Tmio1Servicio;

public interface ServiciosServicio {

	public void addServicio (Tmio1Servicio servicio) throws Exception;
	
	public Tmio1Servicio removeServicio (Tmio1Servicio servicio);
	
	public void setServicio (Tmio1Servicio servicio);
	
	public Tmio1Servicio getServicio (String id);
	
	public List<Tmio1Servicio> getAllServicios();
}
