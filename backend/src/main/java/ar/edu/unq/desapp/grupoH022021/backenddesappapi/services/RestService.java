package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.CriptoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestService {
    @Autowired
    RestTemplate restTemplate;

    public <T> T Get(String uri, Class<T> responseType){
        return restTemplate.getForEntity( uri, responseType).getBody();
    }
}
