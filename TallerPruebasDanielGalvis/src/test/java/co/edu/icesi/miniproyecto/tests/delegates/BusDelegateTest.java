package co.edu.icesi.miniproyecto.tests.delegates;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.ArgumentMatchers;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.util.matcher.AnyRequestMatcher;
import org.springframework.web.client.RestTemplate;

import co.edu.icesi.miniproyecto.delegate.BusDelegate;
import co.edu.icesi.miniproyecto.delegate.BusDelegateImp;
import co.edu.icesi.miniproyecto.model.Tmio1Bus;
import co.edu.icesi.miniproyecto.model.TransactionBody;

@RunWith(MockitoJUnitRunner.class)
public class BusDelegateTest {
	
	@Mock
	private RestTemplate restTemplate;
	
	private BusDelegate busDelegate;
	
	@Before
	public void setUp() {
		BusDelegateImp bdi=new BusDelegateImp();
		bdi.setRestTemplate(restTemplate);
		busDelegate=bdi;
	}
	
	@Test
	public void getAllBusesTest() {
		TransactionBody<List<Tmio1Bus>> body=new TransactionBody<List<Tmio1Bus>>();
		List<Tmio1Bus> list=new ArrayList<Tmio1Bus>();
		Tmio1Bus b=new Tmio1Bus();
		b.setId(1);
		list.add(b);
		b=new Tmio1Bus();
		b.setId(2);
		list.add(b);
		body.setBody(list);
		ResponseEntity<TransactionBody<List<Tmio1Bus>>> response = new ResponseEntity<TransactionBody<List<Tmio1Bus>>>(body,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		Iterable<Tmio1Bus>iter=busDelegate.getAllBuses();
		int cnt=0;
		for (Tmio1Bus tmio1Bus : iter) {
			cnt++;
		}
		assertTrue(cnt==2);
	}
	@Test
	public void addTmio1BusTest() {
		Tmio1Bus b=new Tmio1Bus();
		b.setId(1);
		TransactionBody<Tmio1Bus> body=new TransactionBody<Tmio1Bus>();
		body.setBody(b);
		ResponseEntity<TransactionBody<Tmio1Bus>> response = new ResponseEntity<TransactionBody<Tmio1Bus>>(body,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		b=busDelegate.addTmio1Bus(b);
		assertTrue(b.getId()==1);
	}
	@Test
	public void getTmio1BusTest() {
		Tmio1Bus b=new Tmio1Bus();
		b.setId(1);
		TransactionBody<Tmio1Bus> body=new TransactionBody<Tmio1Bus>();
		body.setBody(b);
		ResponseEntity<TransactionBody<Tmio1Bus>> response = new ResponseEntity<TransactionBody<Tmio1Bus>>(body,HttpStatus.ACCEPTED);
		when(restTemplate.exchange(Mockito.anyString(),Mockito.any(HttpMethod.class),Mockito.any(HttpEntity.class),Mockito.any(ParameterizedTypeReference.class))).thenReturn(response);
		b=busDelegate.getBus(1);
		assertTrue(b.getId()==1);
	}
	
}
