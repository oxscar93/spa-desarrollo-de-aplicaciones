package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Cripto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories.CriptoRepository;

@Service
public class CriptoService {

	@Autowired
	private CriptoRepository repository;
	
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
}
