package co.edu.icesi.miniproyecto.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;

@Repository
public class ConductoresRepositoryImpl implements ConductoresRepository {

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Tmio1Conductore conductor) {
		entityManager.persist(conductor);

	}

	@Override
	public Tmio1Conductore findById(String cedula) {
		return entityManager.find(Tmio1Conductore.class, cedula);
	}

	@Override
	public Iterable<Tmio1Conductore> findAll() {
		String jpql = "Select a from Tmio1Conductore a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void update(Tmio1Conductore conductor) {
		entityManager.merge(conductor);

	}

	@Override
	public void delete(Tmio1Conductore conductor) {
		entityManager.remove(conductor);

	}

	@Override
	public List<Tmio1Conductore> findByName(String name) {
		String jpql = "Select a from Tmio1Conductore a where a.nombre=:nombre";
		return entityManager.createQuery(jpql).setParameter("nombre", name).getResultList();
	}

	@Override
	public List<Tmio1Conductore> findByApellidos(String apellidos) {
		String jpql = "Select a from Tmio1Conductore a where a.apellidos=:apellidos";
		return entityManager.createQuery(jpql).setParameter("apellidos", apellidos).getResultList();
	}

}
