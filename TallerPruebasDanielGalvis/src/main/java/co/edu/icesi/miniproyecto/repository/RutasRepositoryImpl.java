package co.edu.icesi.miniproyecto.repository;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;

@Repository
public class RutasRepositoryImpl implements RutasRepository{

	HashMap<Integer, Tmio1Ruta> rutas = new HashMap<>();
	
	@Override
	public void addRuta(Tmio1Ruta ruta) {
		
		
	}

	@Override
	public Tmio1Ruta removeRuta(int id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setRuta(Tmio1Ruta ruta) {
		
	}

	@Override
	public Tmio1Ruta getRutas(int rutaId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Tmio1Ruta> getAllRutas() {
		// TODO Auto-generated method stub
		return null;
	}

}
