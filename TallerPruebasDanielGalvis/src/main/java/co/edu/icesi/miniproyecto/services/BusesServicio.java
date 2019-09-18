package co.edu.icesi.miniproyecto.services;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;

public interface BusesServicio {

	public void addBus(Tmio1Bus bus);
	
	public Tmio1Bus removeBus (Tmio1Bus bus);
	
	public void setBus (Tmio1Bus bus);
	
	public Tmio1Bus getBus (int ind);
	
	public List<Tmio1Bus> getAllBuses();
}
