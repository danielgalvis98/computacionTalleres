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

import co.edu.icesi.miniproyecto.delegate.BusDelegateImp;
import co.edu.icesi.miniproyecto.delegate.ConductorDelegate;
import co.edu.icesi.miniproyecto.delegate.ConductorDelegateImp;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.Tmio1Conductore;
import co.edu.icesi.miniproyecto.model.TransactionBody;

@RunWith(MockitoJUnitRunner.class)
public class ConductorDelegateTest {
	@Mock
	private RestTemplate restTemplate;
	
	private ConductorDelegate conductorDelegate;
	
	@Before
	public void setUp() {
		ConductorDelegateImp cdi=new ConductorDelegateImp();
		cdi.setRestTemplate(restTemplate);
		conductorDelegate=cdi;
	}
	@Test
	public void getAllConductoresTest() {
		TransactionBody<List<Tmio1Conductore>> body=new TransactionBody<List<Tmio1Conductore>>();
		List<Tmio1Conductore> list=new ArrayList<Tmio1Conductore>();
		Tmio1Conductore c=new Tmio1Conductore();
		c.setCedula("123456789");
		list.add(c);
		c=new Tmio1Conductore();
		c.setCedula("123456");
		list.add(c);
		body.setBody(list);
		ResponseEntity<TransactionBody<List<Tmio1Conductore>>> response = new ResponseEntity<TransactionBody<List<Tmio1Conductore>>>(body,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		Iterable<Tmio1Conductore>iter=conductorDelegate.getAllConductores();
		int cnt=0;
		for (Tmio1Conductore tmio1Conductore : iter) {
			cnt++;
		}
		assertTrue(cnt==2);
	}
	@Test
	public void addConductorTest() {
		Tmio1Conductore c=new Tmio1Conductore();
		c.setCedula("123456789");
		TransactionBody<Tmio1Conductore> body=new TransactionBody<Tmio1Conductore>();
		body.setBody(c);
		ResponseEntity<TransactionBody<Tmio1Conductore>> response = new ResponseEntity<TransactionBody<Tmio1Conductore>>(body,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		c=conductorDelegate.addConductor(c);
		assertTrue(c.getCedula().equals("123456789"));
	}
	@Test
	public void getConductorTest() {
		Tmio1Conductore c=new Tmio1Conductore();
		c.setCedula("123456789");
		TransactionBody<Tmio1Conductore> body=new TransactionBody<Tmio1Conductore>();
		body.setBody(c);
		ResponseEntity<TransactionBody<Tmio1Conductore>> response = new ResponseEntity<TransactionBody<Tmio1Conductore>>(body,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		c=conductorDelegate.getConductor("123456789");
		assertTrue(c.getCedula().equals("123456789"));
	}
}
