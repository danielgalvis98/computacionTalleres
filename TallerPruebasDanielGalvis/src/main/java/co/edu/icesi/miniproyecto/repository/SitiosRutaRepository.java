package co.edu.icesi.miniproyecto.repository;

import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;

public interface SitiosRutaRepository {
	
	public Iterable<Tmio1SitiosRuta> findAll();

	public void addSitioRuta (Tmio1SitiosRuta sitioRuta);
	
	public Tmio1SitiosRuta findById (Tmio1SitiosRutaPK id);
	
	public void updateSitioRuta (Tmio1SitiosRuta sitioRuta);
	
	public void delete(Tmio1SitiosRuta sitioRuta);

}
