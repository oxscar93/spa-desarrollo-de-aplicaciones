package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.NumberFormat;
import java.text.ParseException;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.configuration.EnvironmentConfig;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.ActiveCriptoDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.CriptoDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.DollarDto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Cripto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories.CriptoRepository;

@Service
public class CriptoService {
	
	@Autowired
	private MessageSource messages;

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
		ZoneId zone = ZoneId.systemDefault();
		ZonedDateTime zoneDiteTime = instant.atZone(zone);
		DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.MEDIUM).withLocale(LocaleContextHolder.getLocale());
		String date = zoneDiteTime.format(formatter);

		for (CriptoDto cripto : criptos) {
			ActiveCriptoDto activeCriptoDto = new ActiveCriptoDto();

			activeCriptoDto.name = cripto.symbol;
			Double criptoPrice = !cripto.price.equalsIgnoreCase("") ? Double.parseDouble(cripto.price) : 0;
			String price = String.valueOf(criptoPrice * usd);
			activeCriptoDto.price = this.formatPrice(price);
//			activeCriptoDto.price = price;
			activeCriptoDto.date = date;

			result.add(activeCriptoDto);
		}

		return result;
	}
	
	private String formatPrice(String price) {
		BigDecimal bd = new BigDecimal(price);
		bd = bd.setScale(2, RoundingMode.HALF_UP);
		return String.valueOf(bd.doubleValue());
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
