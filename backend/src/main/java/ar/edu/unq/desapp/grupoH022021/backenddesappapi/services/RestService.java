package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import java.util.List;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {

    RestTemplate restTemplate;

    public RestService(RestTemplate restTemplate){
    	this.restTemplate = restTemplate;
	}

    public <T> T get(String uri, Class<T> responseType){
        return restTemplate.getForEntity(uri, responseType).getBody();
    }

	public ResponseEntity<List> getList(String uri, String headerKey, String headerValue){

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		headers.set(headerKey, headerValue);

		HttpEntity<String> entity = new HttpEntity<String>(null, headers);

		ResponseEntity<List> res = restTemplate.exchange(uri, HttpMethod.GET,entity,List.class);

		return res;
	}
}
