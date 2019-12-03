package co.edu.icesi.miniproyecto.delegate;

import javax.validation.Valid;

import co.edu.icesi.miniproyecto.model.Tmio1Sitio;

public interface SitiosDelegate {

	public Iterable<Tmio1Sitio> getAllSitios();

	public void addTmio1Sitio(Tmio1Sitio tmio1Sitio);

	public Tmio1Sitio getSitioById(long idSitio);

	public void removeSitio(Tmio1Sitio sitioById) throws Exception;

	public void updateTmio1Sitio(Tmio1Sitio tmio1Sitio);

}
