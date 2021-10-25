package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.SellBuyActivity;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories.SellBuyActivitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SellBuyActivitiesService {

    private SellBuyActivitiesRepository repository;

    @Autowired
    public SellBuyActivitiesService(SellBuyActivitiesRepository repository){
        this.repository = repository;
    }

    public List<SellBuyActivity> findAll() {
        return this.repository.findAll();
    }
}
