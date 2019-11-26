package co.edu.icesi.miniproyecto.restControllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

public interface IApiConductor {

	public Iterable<Tmio1Conductore> getConductores();
	
	public void saveConductor(Tmio1Conductore conductor) throws Exception;
	
	public Tmio1Conductore getConductor(String cedula);
}
