package co.edu.icesi.miniproyecto.repository;

import co.edu.icesi.miniproyecto.model.Tmio1Sitio;

public interface SitiosRepository {

	public Iterable<Tmio1Sitio> findAll();

	public void addSitio(Tmio1Sitio sitios);

	public Tmio1Sitio findById(Long id);

	public void updateSitio(Tmio1Sitio sitios);

	public void delete(Tmio1Sitio sitios);

}
