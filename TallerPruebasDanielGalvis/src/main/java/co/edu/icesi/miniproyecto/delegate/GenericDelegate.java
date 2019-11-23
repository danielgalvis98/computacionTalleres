package co.edu.icesi.miniproyecto.delegate;

import org.apache.http.auth.AuthScope;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.CredentialsProvider;
import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.BasicCredentialsProvider;
import org.apache.http.impl.client.HttpClientBuilder;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

public abstract class GenericDelegate {
	RestTemplate restTemplate;
	final String SERVER = "http://localhost:8080/";
	 
    private HttpComponentsClientHttpRequestFactory getClientHttpRequestFactory() 
    {
        HttpComponentsClientHttpRequestFactory clientHttpRequestFactory
                          = new HttpComponentsClientHttpRequestFactory();
         
        clientHttpRequestFactory.setHttpClient(httpClient());
              
        return clientHttpRequestFactory;
    }
     
    private HttpClient httpClient() 
    {
        CredentialsProvider credentialsProvider = new BasicCredentialsProvider();
        
        //BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        
        credentialsProvider.setCredentials(AuthScope.ANY, 
                        new UsernamePasswordCredentials("admin-test","123"));
 
        HttpClient client = HttpClientBuilder
                                .create()
                                .setDefaultCredentialsProvider(credentialsProvider)
                                .build();
        return client;
    }
	
	public GenericDelegate() 
    {
        restTemplate = new RestTemplate(getClientHttpRequestFactory());
    }
}
