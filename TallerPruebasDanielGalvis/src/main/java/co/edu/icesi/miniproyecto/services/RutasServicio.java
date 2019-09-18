package co.edu.icesi.miniproyecto.services;

import java.math.BigDecimal;
import java.util.List;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;

public interface RutasServicio {

	public void addRuta (Tmio1Ruta ruta);
	
	public Tmio1Ruta deleteRuta (Tmio1Ruta ruta);
	
	public void setRuta (Tmio1Ruta ruta);
	
	public Tmio1Ruta getRuta (int id);
	
	public List<Tmio1Ruta> getAllRutas();
}
