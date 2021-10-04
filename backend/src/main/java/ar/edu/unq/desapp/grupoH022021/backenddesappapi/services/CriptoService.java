package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.configuration.EnvironmentConfig;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.ActiveCriptoDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.CriptoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Cripto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories.CriptoRepository;

@Service
public class CriptoService {

	@Autowired
	private CriptoRepository repository;

	@Autowired
	RestService restService;

	@Autowired
	private EnvironmentConfig config;

	@Transactional
	public Cripto save(Cripto model) {
		return this.repository.save(model);
	}

	public Cripto findByID(Integer id) {
		return this.repository.findById(id).get();
	}

	public List<Cripto> findAll() {
		return this.repository.findAll();
	}

	public List<CriptoDto> getPrices(){
		List<Cripto> criptos = findAll();
		List<CriptoDto> result = new ArrayList<CriptoDto>();

		for (Cripto cripto : criptos) {
			result.add(restService.Get(config.getBinanceUrl() + cripto.name,
					                   CriptoDto.class));
		}

		return result;
	}

	public List<ActiveCriptoDto> getActiveCriptos(){
		List<ActiveCriptoDto> result = new ArrayList<ActiveCriptoDto>();
		List<CriptoDto> criptos = this.getPrices();
		Integer usd = this.getPriceUSD();

		for (CriptoDto cripto : criptos) {
			ActiveCriptoDto activeCriptoDto = new ActiveCriptoDto();

			activeCriptoDto.name = cripto.symbol;
			Double criptoPrice = !cripto.price.equalsIgnoreCase("") ? Double.parseDouble(cripto.price) : 0;
			activeCriptoDto.price = String.valueOf(criptoPrice * usd);
			activeCriptoDto.date = LocalDate.now().toString();

			result.add(activeCriptoDto);
		}

		return result;
	}
	
	public Integer getPriceUSD() {
		Integer value = 0;

		ResponseEntity<List> result = restService.getList(config.getBrcaUrl(), "Authorization", "Bearer " + config.getBrcaToken());
		LinkedHashMap body = (LinkedHashMap)result.getBody().get(result.getBody().size() - 1);

		Object resp = body.get("v");

		if (resp.getClass().isInstance(Double.class)) {
			Double resD = (Double) resp;
			value = resD.intValue();
		} else {
			Integer resI = (Integer) resp;
			value = resI.intValue();
		}

		return value;
	}

}
