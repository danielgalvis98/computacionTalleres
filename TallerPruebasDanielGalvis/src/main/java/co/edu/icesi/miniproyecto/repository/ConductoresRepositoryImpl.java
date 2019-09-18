package co.edu.icesi.miniproyecto.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

@Repository
public class ConductoresRepositoryImpl implements ConductoresRepository{

	HashMap<String, Tmio1Conductore> conductores = new HashMap<>();

	@Override
	public void addConductor(Tmio1Conductore conductor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tmio1Conductore removeConductor(Tmio1Conductore conductor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setConductor(Tmio1Conductore conductor) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tmio1Conductore getConductor(String conductorCedula) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tmio1Conductore> getAllConductores() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
