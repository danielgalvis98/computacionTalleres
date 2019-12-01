package co.edu.icesi.miniproyecto.tests.repositories;

import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;
import static org.testng.Assert.assertNull;

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

import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.repository.BusesRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
@SpringBootTest
public class BusDaoTest {
	
	@Autowired
	private BusesRepository tbusDao;
	
	@Before
	public void setupDataBase() {

		if (!tbusDao.findAll().iterator().hasNext()) {
			Tmio1Bus bus = new Tmio1Bus();
			
			bus.setPlaca("ABC");
			bus.setCapacidad(new BigDecimal(20));
			bus.setMarca("Mercedes");
			bus.setModelo(new BigDecimal(80));
			bus.setTipo("T");
			
			tbusDao.save(bus);
			
			
			bus = new Tmio1Bus();
			
			bus.setPlaca("DEF");
			bus.setCapacidad(new BigDecimal(10));
			bus.setMarca("V0lks");
			bus.setModelo(new BigDecimal(20));
			bus.setTipo("T");
			
			tbusDao.save(bus);
			
			
			bus = new Tmio1Bus();
			
			bus.setPlaca("GHI");
			bus.setCapacidad(new BigDecimal(10));
			bus.setMarca("Mercedes");
			bus.setModelo(new BigDecimal(80));
			bus.setTipo("T");
			
			tbusDao.save(bus);
		}
			
	}
	
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() {
		assertNotNull(tbusDao);
		
		Tmio1Bus bus = new Tmio1Bus();
		
		bus.setPlaca("JKL");
		bus.setCapacidad(new BigDecimal(180));
		bus.setMarca("VOlks");
		bus.setModelo(new BigDecimal(2019));
		bus.setTipo("P");
		
		tbusDao.save(bus);
		
		assertEquals(bus, tbusDao.findById(4));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindId() {
		assertNotNull(tbusDao);
		
		Tmio1Bus bus = tbusDao.findById(1);
		
		assertNotNull(bus);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testUpdate() {
		assertNotNull(tbusDao);
		
		Tmio1Bus bus = tbusDao.findById(1);
		bus.setTipo("P");
		
		assertNotNull(bus);
		tbusDao.update(bus);
		
		assertEquals(bus, tbusDao.findById(1));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testRemove() {
		assertNotNull(tbusDao);
		
		Tmio1Bus bus = tbusDao.findById(2);
		
		tbusDao.delete(bus);
		assertNull(tbusDao.findById(2));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByMarca() {
		assertNotNull(tbusDao);
		
		List<Tmio1Bus> buses = tbusDao.findByMarca("Mercedes");
		
		assertEquals(buses.size(), 2);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByModelo() {
		assertNotNull(tbusDao);
		
		List<Tmio1Bus> buses = tbusDao.findByModelo(new BigDecimal(20));
		
		assertEquals(buses.size(), 1);
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindByPlaca() {
		assertNotNull(tbusDao);
		
		Tmio1Bus bus = tbusDao.findByPlaca("GHI");
		
		assertNotNull(bus);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {
		assertNotNull(tbusDao);
		
		Iterable<Tmio1Bus> buses = tbusDao.findAll();
		
		assertTrue(buses.iterator().hasNext());
	}

}
