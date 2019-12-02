package co.edu.icesi.miniproyecto.delegate;

import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;

public interface SitiosRutaDelegate {

	public Iterable<Tmio1SitiosRuta> getAllSitiosRuta();
	
	public Tmio1SitiosRuta addSitioRuta (Tmio1SitiosRuta sitioRuta);
	
	public Tmio1SitiosRuta getSitioRuta (Tmio1SitiosRutaPK key);
	
	public void removeSitioRuta (Tmio1SitiosRuta oldSitioRuta);
}
