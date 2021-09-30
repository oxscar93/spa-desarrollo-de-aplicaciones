package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.ActiveCriptoDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.CriptoDto;
import org.springframework.beans.factory.annotation.Autowired;
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
			result.add(restService.Get("https://api1.binance.com/api/v3/ticker/price?symbol=" + cripto.name,
					                   CriptoDto.class));
		}

		return result;
	}

	public List<ActiveCriptoDto> getActiveCriptos(){
		List<ActiveCriptoDto> result = new ArrayList<ActiveCriptoDto>();
		List<CriptoDto> criptos = this.getPrices();

		for (CriptoDto cripto : criptos) {
			ActiveCriptoDto activeCriptoDto = new ActiveCriptoDto();

			activeCriptoDto.name = cripto.symbol;
			activeCriptoDto.price = cripto.price; //convertir a pesos argentinos con la api
			activeCriptoDto.date = LocalDate.now().toString();

			result.add(activeCriptoDto);
		}

		return result;
	}

}
