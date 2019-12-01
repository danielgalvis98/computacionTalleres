package co.edu.icesi.miniproyecto.tests.delegates;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.miniproyecto.delegate.RutaDelegateImp;
import co.edu.icesi.miniproyecto.delegate.ServicioDelegate;
import co.edu.icesi.miniproyecto.delegate.ServicioDelegateImp;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.Tmio1Servicio;
import co.edu.icesi.miniproyecto.model.Tmio1ServicioPK;
import co.edu.icesi.miniproyecto.model.TransactionBody;

@RunWith(MockitoJUnitRunner.class)
public class ServicioDelegateTest {
	@Mock
	private RestTemplate restTemplate;
	
	private ServicioDelegate servicioDelegate;
	
	@Before
	public void setUp() {
		ServicioDelegateImp rdi=new ServicioDelegateImp();
		rdi.setRestTemplate(restTemplate);
		servicioDelegate=rdi;
	}
	@Test
	public void getAllServiciosTest() {
		TransactionBody<List<Tmio1Servicio>> body=new TransactionBody<List<Tmio1Servicio>>();
		List<Tmio1Servicio> list=new ArrayList<Tmio1Servicio>();
		Tmio1Servicio s=new Tmio1Servicio();
		Tmio1ServicioPK tm1spk=new Tmio1ServicioPK();
		tm1spk.setCedulaConductor("123");
		s.setId(tm1spk);
		list.add(s);
		s=new Tmio1Servicio();
		tm1spk=new Tmio1ServicioPK();
		tm1spk.setCedulaConductor("456");
		s.setId(tm1spk);
		list.add(s);
		body.setBody(list);
		ResponseEntity<TransactionBody<List<Tmio1Servicio>>> response = new ResponseEntity<TransactionBody<List<Tmio1Servicio>>>(body,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		Iterable<Tmio1Servicio>iter=servicioDelegate.getAllServicios();
		int cnt=0;
		for (Tmio1Servicio tmio1Servicio : iter) {
			cnt++;
		}
		assertTrue(cnt==2);
	}
	@Test
	public void addServicioTest() {
		Tmio1Servicio s=new Tmio1Servicio();
		Tmio1ServicioPK tm1spk=new Tmio1ServicioPK();
		tm1spk.setCedulaConductor("123");
		s.setId(tm1spk);
		TransactionBody<Tmio1Servicio> body=new TransactionBody<Tmio1Servicio>();
		body.setBody(s);
		ResponseEntity<TransactionBody<Tmio1Servicio>> response = new ResponseEntity<TransactionBody<Tmio1Servicio>>(body,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		s=servicioDelegate.addServicio(s);
		assertTrue(s.getId().getCedulaConductor().equals("123"));
	}
	@Test
	public void getServicioTest() {
		Tmio1Servicio s=new Tmio1Servicio();
		Tmio1ServicioPK tm1spk=new Tmio1ServicioPK();
		tm1spk.setCedulaConductor("123");
		tm1spk.setIdBus(1);
		tm1spk.setIdRuta(2);
		tm1spk.setFechaFin(LocalDate.MAX);
		tm1spk.setFechaInicio(LocalDate.MIN);;
		
		s.setId(tm1spk);
		TransactionBody<Tmio1Servicio> body=new TransactionBody<Tmio1Servicio>();
		body.setBody(s);
		ResponseEntity<TransactionBody<Tmio1Servicio>> response = new ResponseEntity<TransactionBody<Tmio1Servicio>>(body,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		s=servicioDelegate.getServicio(tm1spk);
		assertTrue(s.getId().getCedulaConductor().equals("123"));
	}
	@Test
	public void removeServicioTest() {
		Tmio1Servicio s=new Tmio1Servicio();
		Tmio1ServicioPK tm1spk=new Tmio1ServicioPK();
		tm1spk.setCedulaConductor("123");
		s.setId(tm1spk);
		TransactionBody<Tmio1Servicio> body=new TransactionBody<Tmio1Servicio>();
		body.setBody(s);
		ResponseEntity<TransactionBody<Tmio1Servicio>> response = new ResponseEntity<TransactionBody<Tmio1Servicio>>(body,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		servicioDelegate.removeServicio(s);
	}
	@Test
	public void getServiciosByDateTest() {
		TransactionBody<List<Tmio1Servicio>> body=new TransactionBody<List<Tmio1Servicio>>();
		List<Tmio1Servicio> list=new ArrayList<Tmio1Servicio>();
		Tmio1Servicio s=new Tmio1Servicio();
		Tmio1ServicioPK tm1spk=new Tmio1ServicioPK();
		tm1spk.setCedulaConductor("123");
		s.setId(tm1spk);
		list.add(s);
		s=new Tmio1Servicio();
		tm1spk=new Tmio1ServicioPK();
		tm1spk.setCedulaConductor("456");
		s.setId(tm1spk);
		list.add(s);
		body.setBody(list);
		ResponseEntity<TransactionBody<List<Tmio1Servicio>>> response = new ResponseEntity<TransactionBody<List<Tmio1Servicio>>>(body,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		Iterable<Tmio1Servicio>iter=servicioDelegate.getServiciosByDate(LocalDate.MAX);
		int cnt=0;
		for (Tmio1Servicio tmio1Servicio : iter) {
			cnt++;
		}
		assertTrue(cnt==2);
	}
	
}
