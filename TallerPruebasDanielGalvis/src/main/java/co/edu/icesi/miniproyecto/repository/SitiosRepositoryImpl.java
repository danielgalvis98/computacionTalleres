package co.edu.icesi.miniproyecto.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Tmio1Sitio;

@Repository
public class SitiosRepositoryImpl implements SitiosRepository {
	
	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public Iterable<Tmio1Sitio> findAll() {
		String jpql = "Select a from Tmio1Sitio a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Transactional
	@Override
	public void addSitio(Tmio1Sitio sitios) {
		entityManager.persist(sitios);
	}

	@Override
	public Tmio1Sitio findById(Long id) {
		return entityManager.find(Tmio1Sitio.class,id);
	}
	
	@Transactional
	@Override
	public void updateSitio(Tmio1Sitio sitios) {
		entityManager.merge(sitios);
	}
	
	@Transactional
	@Override
	public void delete(Tmio1Sitio sitios) {
		entityManager.remove(entityManager.contains(sitios)?sitios:entityManager.merge(sitios));
	}

}
