package co.edu.icesi.miniproyecto.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.repository.ConductoresRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
@SpringBootTest
public class ConductoresDaoTest {
	
	@Autowired
	private ConductoresRepository tConducDao;
	
	@Before
	public void setupDataBase() {
		if (!tConducDao.findAll().iterator().hasNext()) {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
			Tmio1Conductore conduc = new Tmio1Conductore();
			
			conduc.setApellidos("Galvis");
			conduc.setCedula("123");
			conduc.setFechaContratacion(LocalDate.parse("2019-10-10", formatter));
			conduc.setFechaNacimiento(LocalDate.parse("1998-06-03", formatter));
			conduc.setNombre("Daniel");
			
			tConducDao.save(conduc);
			
			
			conduc = new Tmio1Conductore();
			
			conduc.setApellidos("Torres");
			conduc.setCedula("456");
			conduc.setFechaContratacion(LocalDate.parse("2018-02-15", formatter));
			conduc.setFechaNacimiento(LocalDate.parse("1995-05-23", formatter));
			conduc.setNombre("Daniel");
			
			tConducDao.save(conduc);
			
			conduc = new Tmio1Conductore();
			
			conduc.setApellidos("Manchola");
			conduc.setCedula("789");
			conduc.setFechaContratacion(LocalDate.parse("2011-04-01", formatter));
			conduc.setFechaNacimiento(LocalDate.parse("1965-05-03", formatter));
			conduc.setNombre("Hector");
			
			tConducDao.save(conduc);
		}
	}
	

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() {
		assertNotNull(tConducDao);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Tmio1Conductore conduc = new Tmio1Conductore();
		conduc.setApellidos("Duque");
		conduc.setCedula("101");
		conduc.setFechaContratacion(LocalDate.parse("2018-11-21", formatter));
		conduc.setFechaNacimiento(LocalDate.parse("2000-12-24", formatter));
		conduc.setNombre("Juan");
		
		tConducDao.save(conduc);
		
		assertEquals(conduc, tConducDao.findById("101"));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindid() {
		assertNotNull(tConducDao);
		
		Tmio1Conductore conduc = tConducDao.findById("123");
		
		assertNotNull(conduc);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate() {
		assertNotNull(tConducDao);
		
		Tmio1Conductore conduc = tConducDao.findById("456");
		conduc.setApellidos("Alvarez");
		
		tConducDao.update(conduc);
		
		assertEquals(conduc, tConducDao.findById("456"));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testRemove() {
		assertNotNull(tConducDao);
		
		Tmio1Conductore conduc = tConducDao.findById("789");
		
		tConducDao.delete(conduc);
		assertNull(tConducDao.findById("789"));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByNombre() {
		assertNotNull(tConducDao);
		
		List<Tmio1Conductore> conductores = tConducDao.findByName("Daniel");
		
		assertEquals(conductores.size(), 2);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByApellidos() {
		assertNotNull(tConducDao);
		
		List<Tmio1Conductore> conductores = tConducDao.findByApellidos("Galvis");
		
		assertEquals(conductores.size(), 1);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {
		assertNotNull(tConducDao);
		
		Iterable<Tmio1Conductore> conducs = tConducDao.findAll();
		
		assertTrue(conducs.iterator().hasNext());
	}
	
}
