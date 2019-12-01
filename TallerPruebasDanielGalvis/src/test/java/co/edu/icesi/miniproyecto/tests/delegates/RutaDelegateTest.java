package co.edu.icesi.miniproyecto.tests.delegates;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

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

import co.edu.icesi.miniproyecto.delegate.ConductorDelegateImp;
import co.edu.icesi.miniproyecto.delegate.RutaDelegate;
import co.edu.icesi.miniproyecto.delegate.RutaDelegateImp;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.Tmio1Ruta;
import co.edu.icesi.miniproyecto.model.TransactionBody;

@RunWith(MockitoJUnitRunner.class)
public class RutaDelegateTest {
	@Mock
	private RestTemplate restTemplate;
	
	private RutaDelegate rutaDelegate;
	
	@Before
	public void setUp() {
		RutaDelegateImp rdi=new RutaDelegateImp();
		rdi.setRestTemplate(restTemplate);
		rutaDelegate=rdi;
	}
	@Test
	public void getAllRutasTest() {
		TransactionBody<List<Tmio1Ruta>> body=new TransactionBody<List<Tmio1Ruta>>();
		List<Tmio1Ruta> list=new ArrayList<Tmio1Ruta>();
		Tmio1Ruta r=new Tmio1Ruta();
		r.setId(1);
		list.add(r);
		r=new Tmio1Ruta();
		r.setId(2);
		list.add(r);
		body.setBody(list);
		ResponseEntity<TransactionBody<List<Tmio1Ruta>>> response = new ResponseEntity<TransactionBody<List<Tmio1Ruta>>>(body,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		Iterable<Tmio1Ruta>iter=rutaDelegate.getAllRutas();
		int cnt=0;
		for (Tmio1Ruta tmio1Ruta : iter) {
			cnt++;
		}
		assertTrue(cnt==2);
	}
	@Test
	public void addTmio1RutaTest() {
		Tmio1Ruta r=new Tmio1Ruta();
		r.setId(1);
		TransactionBody<Tmio1Ruta> body=new TransactionBody<Tmio1Ruta>();
		body.setBody(r);
		ResponseEntity<TransactionBody<Tmio1Ruta>> response = new ResponseEntity<TransactionBody<Tmio1Ruta>>(body,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		r=rutaDelegate.addTmio1Ruta(r);
		assertTrue(r.getId()==1);
	}
	@Test
	public void getRutaTest() {
		Tmio1Ruta r=new Tmio1Ruta();
		r.setId(1);
		TransactionBody<Tmio1Ruta> body=new TransactionBody<Tmio1Ruta>();
		body.setBody(r);
		ResponseEntity<TransactionBody<Tmio1Ruta>> response = new ResponseEntity<TransactionBody<Tmio1Ruta>>(body,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		r=rutaDelegate.getRuta(1);
		assertTrue(r.getId()==1);
	}
}
