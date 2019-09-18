package co.edu.icesi.miniproyecto.repository;

import java.util.List;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;

public interface BusesRepository {

	public void addBus (Tmio1Bus bus);
	
	public void setBus (Tmio1Bus bus);
	
	public Tmio1Bus removeBus (Tmio1Bus bus);
	
	public Tmio1Bus getBus (int id);
	
	public List<Tmio1Bus> getAllBuses();
}
