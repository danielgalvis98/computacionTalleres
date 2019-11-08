package co.edu.icesi.miniproyecto.services;

import java.util.Date;
import java.util.List;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

public interface ConductoresServicio {

	public void addConductor(Tmio1Conductore conductor) throws Exception;
	
	public Tmio1Conductore removeConductor (Tmio1Conductore conductor);
	
	public void setConductor (Tmio1Conductore conductor);
	
	public Tmio1Conductore getConductor(String cedula);
	
	public Iterable<Tmio1Conductore> getAllConductores();
}
