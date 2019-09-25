package co.edu.icesi.miniproyecto.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;

@Repository
public class BusesRepositoryImpl implements BusesRepository{

	HashMap<Integer, Tmio1Bus> buses = new HashMap<>();

	@Override
	public void addBus(Tmio1Bus bus) {
		buses.put(bus.getId(), bus);
		
	}

	@Override
	public void setBus(Tmio1Bus bus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tmio1Bus removeBus(Tmio1Bus bus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tmio1Bus getBus(int id) {
		return buses.get(id);
	}

	@Override
	public List<Tmio1Bus> getAllBuses() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
