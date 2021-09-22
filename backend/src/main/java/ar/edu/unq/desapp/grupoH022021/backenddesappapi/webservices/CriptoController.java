package ar.edu.unq.desapp.grupoH022021.backenddesappapi.webservices;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Cripto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.CriptoService;

@RestController
@EnableAutoConfiguration
public class CriptoController {

	@Autowired
    private CriptoService criptoService;
	
	@GetMapping("/api/criptos")
    public List<Cripto> criptos() {
        List<Cripto> list = criptoService.findAll();
        return list;
    }
}