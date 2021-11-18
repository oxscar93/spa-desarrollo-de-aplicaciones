package ar.edu.unq.desapp.grupoH022021.backenddesappapi.webservices;

import java.util.List;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.aspects.Log;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.ActiveCriptoDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.CriptoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Cripto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.CriptoService;

@RestController
@EnableAutoConfiguration
public class CriptoController {

	@Autowired
    private CriptoService criptoService;

	@Log
	@GetMapping("/api/criptos")
    public List<Cripto> criptos() {
        List<Cripto> list = criptoService.findAll();
        return list;
    }

    @Log
    @GetMapping("/api/criptos/prices")
    public List<CriptoDto> prices() {
        return criptoService.getPrices();
    }

    @Log
    @GetMapping("/api/criptos/actives")
    public List<ActiveCriptoDto> actives() {
        return criptoService.getActiveCriptos();
    }
}
