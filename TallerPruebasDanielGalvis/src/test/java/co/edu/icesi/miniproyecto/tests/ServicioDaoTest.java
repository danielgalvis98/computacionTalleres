package co.edu.icesi.miniproyecto.tests;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.testng.Assert.assertEquals;

import java.math.BigDecimal;
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

import co.edu.icesi.miniproyecto.dtos.ConducsWithServices;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.repository.BusesRepository;
import co.edu.icesi.miniproyecto.repository.ConductoresRepository;
import co.edu.icesi.miniproyecto.repository.RutasRepository;
import co.edu.icesi.miniproyecto.repository.ServiciosRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@Rollback(false)
@SpringBootTest
public class ServicioDaoTest {
	
	@Autowired
	private ServiciosRepository tServiciosDao;
	
	@Autowired
	private ConductoresRepository tConducDao;
	
	@Autowired
	private BusesRepository tbusDao;
	
	@Autowired
	private RutasRepository tRutasDao;
	
	private Tmio1ServicioPK pk1;
	private Tmio1ServicioPK pk2;
	private Tmio1ServicioPK pk3;
	private Tmio1ServicioPK pk4;
	
	
	public void setupConductores() {
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
	
	
	public void setupRutas() {
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
	
	
	public void setupBuses() {
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
	
	@Before
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void setupDataBase() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		pk1 = new Tmio1ServicioPK();
		pk1.setCedulaConductor("123");
		pk1.setIdBus(1);
		pk1.setIdRuta(1);
		pk1.setFechaInicio(LocalDate.parse("2019-11-11", formatter));
		pk1.setFechaFin(LocalDate.parse("2020-06-11", formatter));
		
		pk2 = new Tmio1ServicioPK();
		pk2.setCedulaConductor("456");
		pk2.setIdRuta(2);
		pk2.setFechaInicio(LocalDate.parse("2019-12-10", formatter));
		pk2.setFechaFin(LocalDate.parse("2025-03-15", formatter));
		pk2.setIdBus(2);
		
		pk3 = new Tmio1ServicioPK();
		pk3.setCedulaConductor("789");
		pk3.setIdBus(3);
		pk3.setIdRuta(3);
		
		pk3.setFechaInicio(LocalDate.parse("2020-01-01", formatter));
		pk3.setFechaFin(LocalDate.parse("2020-12-31", formatter));
		
		pk4 = new Tmio1ServicioPK();
		pk4.setCedulaConductor("789");
		pk4.setIdBus(1);
		pk4.setIdRuta(2);
		
		pk4.setFechaInicio(LocalDate.parse("2019-10-15", formatter));
		pk4.setFechaFin(LocalDate.parse("2026-01-01", formatter));
		if (!tServiciosDao.findAll().iterator().hasNext()) {
			setupBuses();
			setupRutas();
			setupConductores();
			
			
			Tmio1Servicio serv = new Tmio1Servicio();
			Tmio1Conductore conduc1 = tConducDao.findById("123");
			serv.setTmio1Conductore(conduc1);
			
			Tmio1Bus bus1 = tbusDao.findById(1);
			serv.setTmio1Bus(bus1);
			
			
			Tmio1Ruta ruta1 = tRutasDao.findById(1);
			serv.setTmio1Ruta(ruta1);
			
			serv.setId(pk1);
			
			tServiciosDao.save(serv);
			
			
			serv = new Tmio1Servicio();
			Tmio1Conductore conduc2 = tConducDao.findById("456");
			serv.setTmio1Conductore(conduc2);
			
			
			Tmio1Bus bus2 = tbusDao.findById(2);
			serv.setTmio1Bus(bus2);
			
			
			Tmio1Ruta ruta2 = tRutasDao.findById(2);
			serv.setTmio1Ruta(ruta2);
			
			serv.setId(pk2);
			
			tServiciosDao.save(serv);
			
			serv = new Tmio1Servicio();
			Tmio1Conductore conduc3 = tConducDao.findById("789");
			serv.setTmio1Conductore(conduc3);
			
			Tmio1Bus bus3 = tbusDao.findById(3);
			serv.setTmio1Bus(bus3);
			
			Tmio1Ruta ruta3 = tRutasDao.findById(3);
			serv.setTmio1Ruta(ruta3);
			
			serv.setId(pk3);
			
			tServiciosDao.save(serv);
			
			serv = new Tmio1Servicio();
			serv.setTmio1Conductore(conduc3);
			serv.setTmio1Bus(bus1);
			serv.setTmio1Ruta(ruta2);
			
			serv.setId(pk4);
			
			tServiciosDao.save(serv);
		}
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testSave() {
		assertNotNull(tServiciosDao);
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		Tmio1ServicioPK pkTest = new Tmio1ServicioPK();
		
		Tmio1Servicio serv = new Tmio1Servicio();
		Tmio1Conductore conduc1 = tConducDao.findById("123");
		serv.setTmio1Conductore(conduc1);
		pkTest.setCedulaConductor("123");
		
		Tmio1Bus bus1 = tbusDao.findById(1);
		serv.setTmio1Bus(bus1);
		pkTest.setIdBus(1);
		
		Tmio1Ruta ruta1 = tRutasDao.findById(2);
		serv.setTmio1Ruta(ruta1);
		pkTest.setIdRuta(2);
		
		pkTest.setFechaInicio(LocalDate.parse("2020-05-10", formatter));
		pkTest.setFechaFin(LocalDate.parse("2023-06-11", formatter));
		serv.setId(pkTest);
		
		tServiciosDao.save(serv);
		
		assertEquals(serv, tServiciosDao.findById(pkTest));
		
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindid() {
		assertNotNull(tServiciosDao);
		
		Tmio1Servicio serv = tServiciosDao.findById(pk1);
		
		assertNotNull(serv);
		
		
	}
	

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testRemove() {
		assertNotNull(tServiciosDao);
		
		Tmio1Servicio serv = tServiciosDao.findById(pk1);
		tServiciosDao.delete(serv);
		
		assertNull(tServiciosDao.findById(pk1));
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testFindAll() {
		assertNotNull(tServiciosDao);
		
		Iterable<Tmio1Servicio> servs = tServiciosDao.findAll();
		
		assertTrue(servs.iterator().hasNext());
	}

	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testConductoresByServiceDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<ConducsWithServices> cons = tConducDao.findByServiceDates(LocalDate.parse("2020-05-10", formatter));
		assertTrue(cons.size() > 0);
		for (int i = 1; i < cons.size(); i++) {
			Tmio1Conductore conduc = cons.get(i-1).getConduc();
			LocalDate contDate1 = conduc.getFechaContratacion();
			LocalDate nacDate1 = conduc.getFechaNacimiento();
			Tmio1Conductore conduc2 = cons.get(i).getConduc();
			LocalDate contDate2 = conduc2.getFechaContratacion();
			LocalDate nacDate2 = conduc2.getFechaNacimiento();
			assertTrue(contDate1.compareTo(contDate2) < 0 || (contDate1.compareTo(contDate2) == 0
					&& nacDate1.compareTo(nacDate2) <= 0));
		}
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testRutasByServiceDate() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		List<Tmio1Ruta> cons = tRutasDao.findByServiceDate(LocalDate.parse("2019-12-10", formatter));
		assertTrue(cons.size() > 0);
	}
	
	@Test
	@Transactional(readOnly=false, propagation = Propagation.REQUIRED, rollbackFor = Exception.class)
	public void testBusesSameServiceDate() {
		List<Tmio1Bus> cons = tbusDao.find2ServicesSameDay();
		
		assertEquals(cons.size(), 1);
	}
}
