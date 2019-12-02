package co.edu.icesi.miniproyecto.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Tmio1SitiosRuta;
import co.edu.icesi.miniproyecto.model.Tmio1SitiosRutaPK;

@Repository
public class SitiosRutaRepositoryImpl implements SitiosRutaRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	
	@Override
	public Iterable<Tmio1SitiosRuta> findAll() {
		String jpql = "Select a FROM Tmio1SitiosRuta";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void addSitioRuta(Tmio1SitiosRuta sitioRuta) {
		entityManager.persist(sitioRuta);

	}

	@Override
	public Tmio1SitiosRuta findById(Tmio1SitiosRutaPK id) {
		return entityManager.find(Tmio1SitiosRuta.class, id);
	}

	@Override
	public void updateSitioRuta(Tmio1SitiosRuta sitioRuta) {
		entityManager.merge(sitioRuta);

	}

	@Override
	public void delete(Tmio1SitiosRuta sitioRuta) {
		entityManager.remove(entityManager.contains(sitioRuta)?sitioRuta:entityManager.merge(sitioRuta));

	}

}
