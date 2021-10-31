package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.configuration.EnvironmentConfig;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.ActiveCriptoDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.CriptoDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.DollarDto;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.utils.DateTimeUtils;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.utils.NumberUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Cripto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories.CriptoRepository;

@Service
public class CriptoService {

	private CriptoRepository repository;

	RestService restService;

	private EnvironmentConfig config;

	@Autowired
	public CriptoService(CriptoRepository repository, RestService restService, EnvironmentConfig config){
		this.config = config;
		this.restService = restService;
		this.repository = repository;
	}

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
			result.add(restService.get(config.getBinanceUrl() + cripto.name,
					                   CriptoDto.class));
		}

		return result;
	}
	
	public List<ActiveCriptoDto> getActiveCriptos(){

		List<ActiveCriptoDto> result = new ArrayList<ActiveCriptoDto>();
		List<CriptoDto> criptos = this.getPrices();
		double usd = this.getPriceUSD();
		Instant instant = Instant.now();

		String date =  DateTimeUtils.formatDate(instant);

		for (CriptoDto cripto : criptos) {
			ActiveCriptoDto activeCriptoDto = new ActiveCriptoDto();

			activeCriptoDto.name = cripto.symbol;
			Double criptoPrice = !cripto.price.equalsIgnoreCase("") ? Double.parseDouble(cripto.price) : 0;
			Double price = criptoPrice * usd;
			activeCriptoDto.price = NumberUtils.formatWithCurrency(price);
			activeCriptoDto.date = date;
			activeCriptoDto.dateTime = instant.toString();
			activeCriptoDto.priceNumber = price;

			result.add(activeCriptoDto);
		}

		return result;
	}

	public Double getPriceUSD() {
		String price = restService.get(config.getDollarPrice(), DollarDto.class).getCompra();
		NumberFormat nf = NumberFormat.getInstance();
		double number = 0.0;
		try {
			number = nf.parse(price).doubleValue();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return number/100.00;
	}

}
