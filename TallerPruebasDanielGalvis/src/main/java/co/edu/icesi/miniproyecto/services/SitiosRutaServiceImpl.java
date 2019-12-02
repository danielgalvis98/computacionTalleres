package co.edu.icesi.miniproyecto.services;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Sitio;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;
import co.edu.icesi.miniproyecto.repository.RutasRepository;
import co.edu.icesi.miniproyecto.repository.SitiosRepository;
import co.edu.icesi.miniproyecto.repository.SitiosRutaRepository;

@Service
public class SitiosRutaServiceImpl implements SitiosRutaService {

	private SitiosRutaRepository sitioRutaRepository;
	private SitiosRepository sitiosRepository;
	private RutasRepository rutasRepository;
	
	@Autowired
	public SitiosRutaServiceImpl(SitiosRutaRepository sitioRuta, SitiosRepository sitios,
			RutasRepository rutas) {
		sitioRutaRepository = sitioRuta;
		sitiosRepository = sitios;
		rutasRepository = rutas;
	}
	
	@Override
	@Transactional
	public void addSitioRuta(Tmio1SitiosRuta sitioRuta) {
		if (sitioRuta == null)
			throw new NullPointerException("El sitio-ruta no puede ser nulo");
		
		Tmio1SitiosRutaPK pk = sitioRuta.getId();
		Tmio1Sitio sitio = sitiosRepository.findById((long)pk.getIdSitio());
		if (sitio == null)
			throw new RuntimeException("El sitio debe de estar registrado");
		
		Tmio1Ruta ruta = rutasRepository.findById(pk.getIdRuta());
		if (ruta == null)
			throw new RuntimeException("La ruta debe de estar registada");
		
		sitioRutaRepository.addSitioRuta(sitioRuta);
	}

	@Override
	@Transactional
	public Tmio1SitiosRuta removeSitioRuta(Tmio1SitiosRuta sitioRuta) {
		sitioRutaRepository.delete(sitioRuta);
		return sitioRuta;
	}

	@Override
	@Transactional
	public void setSitioRuta(Tmio1SitiosRuta sitioRuta) {
		Tmio1SitiosRuta sitioR = sitioRutaRepository.findById(sitioRuta.getId());
		if (sitioR == null)
			throw new RuntimeException("No hay ningun sitio-ruta con ese id");
		addSitioRuta(sitioRuta);
	}

	@Override
	@Transactional
	public Tmio1SitiosRuta getSitioRuta(Tmio1SitiosRutaPK id) {
		return sitioRutaRepository.findById(id);
	}

	@Override
	@Transactional
	public Iterable<Tmio1SitiosRuta> getAllSitiosRuta() {
		return sitioRutaRepository.findAll();
	}

}
