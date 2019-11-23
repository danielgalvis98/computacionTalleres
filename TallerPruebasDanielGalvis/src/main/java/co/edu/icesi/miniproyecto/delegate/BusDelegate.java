package co.edu.icesi.miniproyecto.delegate;

import javax.validation.Valid;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;

public interface BusDelegate {

	public Iterable<Tmio1Bus> getAllBuses();

	public Tmio1Bus getBus(Integer idBus);

	Tmio1Bus addTmio1Bus(Tmio1Bus bus);

}
