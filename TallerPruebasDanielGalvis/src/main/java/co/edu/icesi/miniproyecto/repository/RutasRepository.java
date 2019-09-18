package co.edu.icesi.miniproyecto.repository;

import java.util.List;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;

public interface RutasRepository {
	
	public void addRuta(Tmio1Ruta ruta);
	
	public Tmio1Ruta removeRuta(int id);
	
	public void setRuta(Tmio1Ruta ruta);
	
	public Tmio1Ruta getRutas (int rutaId);
	
	public List<Tmio1Ruta> getAllRutas();
}
