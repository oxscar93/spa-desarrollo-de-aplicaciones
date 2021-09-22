package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import javax.annotation.PostConstruct;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Cripto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.User;

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

	@PostConstruct
	public void initialize() {
		if (className.equals("org.h2.Driver")) {
			logger.warn("Init Data Using H2 DB");
			fireInitialDataUser();
			fireInitialDataCripto();
		}
	}

	private void fireInitialDataUser() {
		User user = new User(1, "guille", "salvatore");
		User user2 = new User(2, "oscar", "lescano");

		userService.save(user);
		userService.save(user2);
	}
	
	private void fireInitialDataCripto() {
		Cripto cripto = new Cripto(1, "ETHUSDT", 2920D);
		Cripto cripto2 = new Cripto(2, "BNBUSDT", 70000D);

		criptoService.save(cripto);
		criptoService.save(cripto2);
	}
}
