package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.SellBuyActivityDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.SellBuyActivity;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories.SellBuyActivitiesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.util.List;
import java.util.Locale;

@Service
public class SellBuyActivitiesService {

    private SellBuyActivitiesRepository repository;

    @Autowired
    public SellBuyActivitiesService(SellBuyActivitiesRepository repository){
        this.repository = repository;
    }

    public SellBuyActivity findByID(Integer id) {
        return this.repository.findById(id).get();
    }

    public List<SellBuyActivity> findAll() {
        return this.repository.findAll();
    }

    public void buy(SellBuyActivityDto sellBuyActivityDto) {
        SellBuyActivity entity = createEntity(sellBuyActivityDto, 1);

        this.repository.save(entity);
    }

    public void sell(SellBuyActivityDto sellBuyActivityDto) {
        SellBuyActivity entity = createEntity(sellBuyActivityDto, 2);

        this.repository.save(entity);
    }

    private SellBuyActivity createEntity(SellBuyActivityDto sellBuyActivityDto, int type){
        SellBuyActivity entity = new SellBuyActivity();

        DateTimeFormatter format = new DateTimeFormatterBuilder()
                .parseCaseInsensitive()
                .appendPattern("yyyy-MM-dd")
                .toFormatter(Locale.ENGLISH);

        entity.date = LocalDate.parse(sellBuyActivityDto.date, format);
        entity.user = sellBuyActivityDto.user;
        entity.cripto = sellBuyActivityDto.cripto;
        entity.criptoPrice = sellBuyActivityDto.criptoPrice;
        entity.operationAmount = sellBuyActivityDto.criptoCount * sellBuyActivityDto.criptoPrice;
        entity.criptoCount = sellBuyActivityDto.criptoCount;
        entity.reputationPoints = 0;
        entity.operationCount = 0;
        entity.type = type;

        return entity;
    }
}
