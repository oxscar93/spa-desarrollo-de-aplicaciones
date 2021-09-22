package ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Cripto;

public interface CriptoRepository extends CrudRepository<Cripto, Integer> {

	Optional<Cripto> findById(Integer id);
	
	List<Cripto> findAll();
}
