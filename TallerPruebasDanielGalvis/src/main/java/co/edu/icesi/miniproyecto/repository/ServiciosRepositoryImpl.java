package co.edu.icesi.miniproyecto.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Tmio1Servicio;

@Repository
public class ServiciosRepositoryImpl implements ServiciosRepository{

	HashMap<String, Tmio1Servicio> servicios;

	@Override
	public void addServicio(Tmio1Servicio servicio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tmio1Servicio removeServicio(Tmio1Servicio servicio) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setServicio(Tmio1Servicio servicio) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Tmio1Servicio getServicio(String servicioId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tmio1Servicio> getAllServicios() {
		// TODO Auto-generated method stub
		return null;
	}
	
	
}