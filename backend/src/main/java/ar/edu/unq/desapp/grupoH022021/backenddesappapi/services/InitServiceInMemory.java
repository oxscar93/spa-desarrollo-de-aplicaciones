package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import javax.annotation.PostConstruct;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.configuration.EnvironmentConfig;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.SellBuyActivityDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.UserDto;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Cripto;

import java.time.Instant;

@Service
@Transactional
public class InitServiceInMemory {

	protected final Log logger = LogFactory.getLog(getClass());

	@Value("${spring.datasource.driverClassName:NONE}")
	private String className;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private CriptoService criptoService;

	@Autowired
	private SellBuyActivitiesService sellBuyActivitiesService;

	@Autowired
	private EnvironmentConfig config;

	@PostConstruct
	public void initialize() {
		if (className.equals("org.h2.Driver")) {
			logger.warn("Init Data Using H2 DB");
			fireInitialDataUser();
			fireInitialDataCripto();
			fireInitialSellBuyActivities();
		}
	}

	private void fireInitialDataUser() {
		if (!config.isFakeData())
			return;

		UserDto user = new UserDto();

		user.name = "guille";
		user.lastName = "salvatore";
		user.password = "1234";

		UserDto user2 = new UserDto();

		user2.name = "osc";
		user2.lastName = "lescano";
		user2.password = "1234";

		userService.save(user);
		userService.save(user2);
	}

	private void fireInitialSellBuyActivities() {
		if (!config.isFakeData())
			return;

		SellBuyActivityDto s = new SellBuyActivityDto();

		s.dateTime = Instant.now();
		s.operationAmount = 10.0;
		s.cripto = "AXSUSDT";
		s.criptoCount = 8;
		s.user = "osc";
		s.criptoPrice = 10.0;

		sellBuyActivitiesService.sell(s);

		SellBuyActivityDto s2 = new SellBuyActivityDto();

		s2.dateTime = Instant.now();
		s2.operationAmount = 10.0;
		s2.cripto = "ATOMUSDT";
		s2.criptoCount = 8;
		s2.user = "guille";
		s2.criptoPrice = 10.0;

		sellBuyActivitiesService.buy(s2);
	}
	
	private void fireInitialDataCripto() {
		Cripto cripto = new Cripto(1, "ALICEUSDT");
		Cripto cripto2 = new Cripto(2, "MATICUSDT");
		Cripto cripto3 = new Cripto(3, "AXSUSDT");
		Cripto cripto4 = new Cripto(4, "AAVEUSDT");
		Cripto cripto5 = new Cripto(5, "ATOMUSDT");
		Cripto cripto6 = new Cripto(6, "NEOUSDT");
		Cripto cripto7 = new Cripto(7, "DOTUSDT");
		Cripto cripto8 = new Cripto(8, "ETHUSDT");
		Cripto cripto9 = new Cripto(9, "CAKEUSDT");
		Cripto cripto10 = new Cripto(10, "BTCUSDT");
		Cripto cripto11 = new Cripto(11, "BNBUSDT");

		criptoService.save(cripto);
		criptoService.save(cripto2);
		criptoService.save(cripto3);
		criptoService.save(cripto4);
		criptoService.save(cripto5);
		criptoService.save(cripto6);
		criptoService.save(cripto7);
		criptoService.save(cripto8);
		criptoService.save(cripto9);
		criptoService.save(cripto10);
		criptoService.save(cripto11);
	}
}
