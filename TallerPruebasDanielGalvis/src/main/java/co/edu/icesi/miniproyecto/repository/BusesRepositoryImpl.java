package co.edu.icesi.miniproyecto.repository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Tmio1Bus;

@Repository
public class BusesRepositoryImpl implements BusesRepository{
	
	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public void save(Tmio1Bus bus) {
		entityManager.persist(bus);
		
	}

	@Override
	public Tmio1Bus findById(int id) {
		return entityManager.find(Tmio1Bus.class, id);
	}

	@Override
	public void update(Tmio1Bus bus) {
		entityManager.merge(bus);
	}

	@Override
	public void delete(Tmio1Bus bus) {
		entityManager.remove(bus);
		
	}

	@Override
	public Tmio1Bus findByPlaca(String placa) {
		String jpql = "Select a from Tmio1Bus a where a.placa =:placa";
		return (Tmio1Bus) entityManager.createQuery(jpql).setParameter("placa", placa).getSingleResult();
	}
	
	@Override
	public List<Tmio1Bus> findByMarca(String marca) {
		String jpql = "Select a from Tmio1Bus a where a.marca =:marca";
		return entityManager.createQuery(jpql).setParameter("marca", marca).getResultList();
	}

	@Override
	public List<Tmio1Bus> findByModelo(BigDecimal modelo) {
		String jpql = "Select a from Tmio1Bus a where a.modelo =:modelo";
		return entityManager.createQuery(jpql).setParameter("modelo", modelo).getResultList();
	}

	@Override
	public Iterable<Tmio1Bus> findAll() {
		String jpql = "Select a from Tmio1Bus a";
		return entityManager.createQuery(jpql).getResultList();
	}


	@Override
	public List<Tmio1Bus> find2ServicesSameDay() {
		String jpql = "SELECT b FROM Tmio1Bus b JOIN Tmio1Servicio s ON b.id = s.id.idBus "
				+ "JOIN Tmio1Servicio c ON (b.id = c.id.idBus AND s.id != c.id) "
				+ "WHERE (s.id.fechaInicio >= c.id.fechaInicio) OR (s.id.fechaFin <= c.id.fechaFin) "
				+ "OR (s.id.fechaInicio < c.id.fechaInicio AND s.id.fechaFin > c.id.fechaFin) "
				+ "GROUP BY b.id HAVING COUNT (s) > 1";
		return entityManager.createQuery(jpql).getResultList();
	}
	
	

}
