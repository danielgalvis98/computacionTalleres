package co.edu.icesi.miniproyecto.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.model.Tmio1Sitio;
import co.edu.icesi.miniproyecto.repository.SitiosRepository;

@Service
public class SitiosServicioImpl implements SitiosServicio {
	
	private SitiosRepository sitiosRepository;
	
	@Autowired
	public SitiosServicioImpl(SitiosRepository sitiosRepository) {
		this.sitiosRepository=sitiosRepository;
	}
	
	@Override
	@Transactional
	public Iterable<Tmio1Sitio> getAllSitios() {
		return sitiosRepository.findAll();
	}

	@Override
	@Transactional
	public void addSitio(Tmio1Sitio sitios) {
		sitiosRepository.addSitio(sitios);
	}

	@Override
	@Transactional
	public Tmio1Sitio getSitio(Long id) {
		return sitiosRepository.findById(id);
	}

	@Override
	@Transactional
	public void updateSitio(Tmio1Sitio sitios) {
		sitiosRepository.updateSitio(sitios);
	}

	@Override
	@Transactional
	public void removeSitio(Tmio1Sitio sitios) {
		sitiosRepository.delete(sitios);
	}

}
