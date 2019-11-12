package co.edu.icesi.miniproyecto.repository;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;

@Repository
public class RutasRepositoryImpl implements RutasRepository {

	@PersistenceContext
	private EntityManager entityManager;
	@Override
	public void save(Tmio1Ruta ruta) {
		entityManager.persist(ruta);

	}

	@Override
	public Tmio1Ruta findById(int id) {
		return entityManager.find(Tmio1Ruta.class, id);
	}

	@Override
	public Iterable<Tmio1Ruta> findAll() {
		String jpql = "SELECT a FROM Tmio1Ruta a";
		return entityManager.createQuery(jpql).getResultList();
	}

	@Override
	public void update(Tmio1Ruta ruta) {
		entityManager.merge(ruta);

	}

	@Override
	public void delete(Tmio1Ruta ruta) {
		entityManager.remove(ruta);

	}

	@Override
	public List<Tmio1Ruta> findByHours(BigDecimal horaInicio, BigDecimal horaFin) {
		String jpql = "SELECT a FROM Tmio1Ruta a WHERE a.horaInicio >=:horaInicio"
				+ " AND a.horaFin <=:horaFin";
		return entityManager.createQuery(jpql).setParameter("horaInicio", horaInicio)
				.setParameter("horaFin", horaFin).getResultList();
	}

	@Override
	public List<Tmio1Ruta> findByDates(BigDecimal diaInicio, BigDecimal diaFin) {
		String jpql = "SELECT a FROM Tmio1Ruta a WHERE a.diaInicio >=:diaInicio"
				+ " AND a.diaFin <=:diaFin";
		return entityManager.createQuery(jpql).setParameter("diaInicio", diaInicio)
				.setParameter("diaFin", diaFin).getResultList();
	}

	@Override
	public List<Tmio1Ruta> findByServiceDate(LocalDate date) {
		String jpql = "SELECT r FROM Tmio1Ruta r JOIN Tmio1Servicio s ON r.id = s.id.idRuta "
				+ "WHERE :date BETWEEN s.id.fechaInicio AND s.id.fechaFin "
				+ "GROUP BY r HAVING COUNT(s) BETWEEN 1 AND 9";
		return entityManager.createQuery(jpql).setParameter("date", date).getResultList();
	}

}
