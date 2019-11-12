package co.edu.icesi.miniproyecto.repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;

@Repository
public class ServiciosRepositoryImpl implements ServiciosRepository{

	@PersistenceContext
	private EntityManager entityManager;
	
	@Override
	public void save(Tmio1Servicio serv) {
		entityManager.persist(serv);
		
	}

	@Override
	public Tmio1Servicio findById(Tmio1ServicioPK id) {
		// TODO Auto-generated method stub
		return entityManager.find(Tmio1Servicio.class, id);
	}

	@Override
	public Iterable<Tmio1Servicio> findAll() {
		String jpql = "SELECT a FROM Tmio1Servicio a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void delete(Tmio1Servicio serv) {
		entityManager.remove(serv);
		
	}

}
