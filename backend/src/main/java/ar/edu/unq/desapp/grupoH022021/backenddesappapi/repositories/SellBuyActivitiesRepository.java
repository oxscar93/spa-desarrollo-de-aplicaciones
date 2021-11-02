package ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.SellBuyActivity;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Configuration
@Repository
public interface SellBuyActivitiesRepository extends CrudRepository<SellBuyActivity, Integer> {
	List<SellBuyActivity> findAll();
	Optional<SellBuyActivity> findById(Integer id);
}
