package co.edu.icesi.miniproyecto.services;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.repository.BusesRepository;

@Service
public class BusesServicioImpl implements BusesServicio{

	private BusesRepository busRepo;
	
	@Autowired
	public BusesServicioImpl(BusesRepository repo) {
		busRepo = repo;
	}
	
	@Override
	public void addBus(Tmio1Bus bus) {
		if (bus == null) {
			throw new NullPointerException("El bus no puede ser nulo");
		}
		busRepo.addBus(bus);
		
	}

	@Override
	public Tmio1Bus removeBus(Tmio1Bus bus) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setBus(Tmio1Bus bus) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tmio1Bus getBus(int ind) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tmio1Bus> getAllBuses() {
		// TODO Auto-generated method stub
		return null;
	}

	

}
