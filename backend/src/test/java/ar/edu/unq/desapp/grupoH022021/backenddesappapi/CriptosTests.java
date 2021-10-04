package ar.edu.unq.desapp.grupoH022021.backenddesappapi;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.configuration.EnvironmentConfig;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.ActiveCriptoDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.CriptoDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Cripto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories.CriptoRepository;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.CriptoService;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.RestService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.mockito.stubbing.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.test.util.AssertionErrors.assertTrue;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withStatus;

@SpringBootTest
public class CriptosTests {
    @Autowired
    private CriptoService criptoService;

    public CriptosTests(){

    }

    @Test
    public void getAllCriptos() {
        List<Cripto> criptos = this.criptoService.findAll();

        assertTrue("Criptos not found", criptos.size() == 11);
    }

    @Test
    public void getPrices() throws URISyntaxException, JsonProcessingException {
        List<CriptoDto> criptos = this.criptoService.getPrices();

        assertTrue("Prices not found", criptos.size() == 11);
    }

    @Test
    public void getActiveCriptos() throws URISyntaxException, JsonProcessingException {
        List<ActiveCriptoDto> criptos = this.criptoService.getActiveCriptos();

        assertTrue("Active Criptos not found", criptos.size() == 11);
    }
}
