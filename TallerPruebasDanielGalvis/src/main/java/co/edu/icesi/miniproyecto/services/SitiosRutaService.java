package co.edu.icesi.miniproyecto.services;

import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;

public interface SitiosRutaService {
	
	public void addSitioRuta (Tmio1SitiosRuta sitioRuta);
	
	public Tmio1SitiosRuta removeSitioRuta (Tmio1SitiosRuta sitioRuta);
	
	public void setSitioRuta (Tmio1SitiosRuta sitioRuta);
	
	public Tmio1SitiosRuta getSitioRuta (Tmio1SitiosRutaPK id);
	
	public Iterable<Tmio1SitiosRuta> getAllSitiosRuta();

}
