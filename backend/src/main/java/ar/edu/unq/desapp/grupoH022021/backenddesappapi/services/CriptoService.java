package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.configuration.EnvironmentConfig;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.ActiveCriptoDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.CriptoDto;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
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

		List<ActiveCriptoDto> result2 = new ArrayList<ActiveCriptoDto>();
		ActiveCriptoDto c = new ActiveCriptoDto();
		c.price = "10";
		c.name = "asd";
		c.date = LocalDate.now().toString();
		result2.add(c);
		return result2;

//		List<ActiveCriptoDto> result = new ArrayList<ActiveCriptoDto>();
//		List<CriptoDto> criptos = this.getPrices();
//		Integer usd = this.getPriceUSD();
//		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
//
//		for (CriptoDto cripto : criptos) {
//			ActiveCriptoDto activeCriptoDto = new ActiveCriptoDto();
//
//			activeCriptoDto.name = cripto.symbol;
//			Double criptoPrice = !cripto.price.equalsIgnoreCase("") ? Double.parseDouble(cripto.price) : 0;
//			Integer criptoPriceI = criptoPrice.intValue();
//			String priceI = String.valueOf(criptoPriceI * usd);
////			String price = String.valueOf(criptoPrice * usd);
////			activeCriptoDto.price = this.formatPrice(priceI);
//			activeCriptoDto.price = priceI;
//			LocalDateTime now = LocalDateTime.now();
//			activeCriptoDto.date = now.format(format);
//
//			result.add(activeCriptoDto);
//		}
//
//		return result;
	}
	
	private String formatPrice(String price) {
		BigDecimal bd = new BigDecimal(price);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return String.valueOf(bd.doubleValue());
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
