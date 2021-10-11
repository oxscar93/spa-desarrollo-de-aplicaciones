package ar.edu.unq.desapp.grupoH022021.backenddesappapi;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.ActiveCriptoDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.CriptoDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Cripto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.CriptoService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.springframework.test.util.AssertionErrors.assertTrue;

import java.net.URISyntaxException;
import java.util.List;


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

        assertTrue("Prices not found", criptos.get(0).getSymbol().equalsIgnoreCase("ALICEUSDT"));
        assertTrue("Prices not found", criptos.size() == 11);
    }

    @Test
    public void getActiveCriptos() throws URISyntaxException, JsonProcessingException {
        List<ActiveCriptoDto> criptos = this.criptoService.getActiveCriptos();

        assertTrue("Active Criptos not found", criptos.size() == 11);
    }
}
