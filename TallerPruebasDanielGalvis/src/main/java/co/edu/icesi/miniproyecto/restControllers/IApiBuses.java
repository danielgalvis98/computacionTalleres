package co.edu.icesi.miniproyecto.restControllers;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;

public interface IApiBuses {

	public Iterable<Tmio1Bus> getBuses();
	
	public Tmio1Bus addTmio1Bus(Tmio1Bus bus);
	
	public Tmio1Bus getBus(Integer id);
}
