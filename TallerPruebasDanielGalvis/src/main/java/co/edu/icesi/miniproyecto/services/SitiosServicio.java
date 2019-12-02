package co.edu.icesi.miniproyecto.services;

import co.edu.icesi.miniproyecto.model.Tmio1Sitio;

public interface SitiosServicio {

	public Iterable<Tmio1Sitio> getAllSitios();

	public void addSitio(Tmio1Sitio sitios);

	public Tmio1Sitio getSitio(Long id);

	public void updateSitio(Tmio1Sitio sitios);

	public void removeSitio(Tmio1Sitio sitios);
}
