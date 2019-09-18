package co.edu.icesi.miniproyecto.repository;

import java.util.List;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

public interface ConductoresRepository {

	public void addConductor(Tmio1Conductore conductor);
	
	public Tmio1Conductore removeConductor (Tmio1Conductore conductor);
	
	public void setConductor (Tmio1Conductore conductor);
	
	public Tmio1Conductore getConductor (String conductorCedula);
	
	public List<Tmio1Conductore> getAllConductores ();
}
