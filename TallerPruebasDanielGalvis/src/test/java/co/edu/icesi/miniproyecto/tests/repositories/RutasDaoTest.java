package co.edu.icesi.miniproyecto.tests.repositories;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.repository.RutasRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
@SpringBootTest
public class RutasDaoTest {
	
	@Autowired
	private RutasRepository tRutasDao;
	
	@Before
	public void setupDataBase() {
		if (!tRutasDao.findAll().iterator().hasNext()) {
			Tmio1Ruta ruta = new Tmio1Ruta();
			
			ruta.setActiva("Y");
			ruta.setDescripcion("Paso del Comercio");
			ruta.setDiaFin(new BigDecimal(5));
			ruta.setDiaInicio(new BigDecimal(2));
			ruta.setHoraFin(new BigDecimal(5000));
			ruta.setHoraInicio(new BigDecimal(1000));
			ruta.setNumero("T31");
			tRutasDao.save(ruta);
			
			ruta = new Tmio1Ruta();
			
			ruta.setActiva("Y");
			ruta.setDescripcion("Terminal Menga");
			ruta.setDiaFin(new BigDecimal(5));
			ruta.setDiaInicio(new BigDecimal(1));
			ruta.setHoraFin(new BigDecimal(8000));
			ruta.setHoraInicio(new BigDecimal(3000));
			ruta.setNumero("E21");
			tRutasDao.save(ruta);
			
			ruta = new Tmio1Ruta();
			
			ruta.setActiva("N");
			ruta.setDescripcion("Universidades");
			ruta.setDiaFin(new BigDecimal(6));
			ruta.setDiaInicio(new BigDecimal(1));
			ruta.setHoraFin(new BigDecimal(8000));
			ruta.setHoraInicio(new BigDecimal(1000));
			ruta.setNumero("E31");
			tRutasDao.save(ruta);
		}
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() {
		assertNotNull(tRutasDao);
		Tmio1Ruta ruta = new Tmio1Ruta();
		
		ruta.setActiva("N");
		ruta.setDescripcion("Andr�s Sanin");
		ruta.setDiaFin(new BigDecimal(6));
		ruta.setDiaInicio(new BigDecimal(1));
		ruta.setHoraFin(new BigDecimal(8000));
		ruta.setHoraInicio(new BigDecimal(1000));
		ruta.setNumero("E41");
		tRutasDao.save(ruta);
		
		assertEquals(ruta, tRutasDao.findById(4));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindId() {
		assertNotNull(tRutasDao);
		
		Tmio1Ruta ruta = tRutasDao.findById(1);
		
		assertNotNull(ruta);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate() {
		assertNotNull(tRutasDao);
		
		Tmio1Ruta ruta = tRutasDao.findById(2);
		ruta.setActiva("N");
		
		tRutasDao.update(ruta);
		assertEquals(ruta, tRutasDao.findById(2));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testRemove () {
		assertNotNull(tRutasDao);
		
		Tmio1Ruta ruta = tRutasDao.findById(3);
		
		tRutasDao.delete(ruta);
		
		assertNull(tRutasDao.findById(3));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {
		assertNotNull(tRutasDao);
		
		Iterable<Tmio1Ruta> rutas = tRutasDao.findAll();
		
		assertTrue(rutas.iterator().hasNext());
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByDays() {
		assertNotNull(tRutasDao);
		
		List<Tmio1Ruta> rutas = tRutasDao.findByDates(new BigDecimal(1), new BigDecimal(5));
		
		assertEquals(rutas.size(), 2);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByHours(){
		assertNotNull(tRutasDao);
		
		List<Tmio1Ruta> rutas = tRutasDao.findByHours(new BigDecimal(2000), new BigDecimal(9000));
		
		assertEquals(rutas.size(), 1);
	}

}
