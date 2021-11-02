package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.SellBuyActivityDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.SellBuyActivity;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories.SellBuyActivitiesRepository;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.utils.DateTimeUtils;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.utils.NumberUtils;
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

    public SellBuyActivity findByID(Integer id) {
        return this.repository.findById(id).get();
    }

    public List<SellBuyActivity> findAll() {
        return this.repository.findAll();
    }

    public SellBuyActivity buy(SellBuyActivityDto sellBuyActivityDto) {
        SellBuyActivity entity = createEntity(sellBuyActivityDto, 2);

        this.repository.save(entity);

        SellBuyActivity r = this.repository.save(entity);

        return r;
    }

    public SellBuyActivity sell(SellBuyActivityDto sellBuyActivityDto) {
        SellBuyActivity entity = createEntity(sellBuyActivityDto, 1);

        this.repository.save(entity);

        SellBuyActivity r = this.repository.save(entity);

        return r;
    }

    private SellBuyActivity createEntity(SellBuyActivityDto sellBuyActivityDto, int type){
        SellBuyActivity entity = new SellBuyActivity();

        entity.dateTimeFormatted = DateTimeUtils.formatDate(sellBuyActivityDto.dateTime);
        entity.date = sellBuyActivityDto.dateTime;
        entity.user = sellBuyActivityDto.user;
        entity.criptoPriceFormatted = NumberUtils.formatWithCurrency(sellBuyActivityDto.criptoPrice);
        entity.cripto = sellBuyActivityDto.cripto;
        entity.criptoPrice = sellBuyActivityDto.criptoPrice;
        entity.operationAmountFormatted = NumberUtils.formatWithCurrency(sellBuyActivityDto.operationAmount);
        entity.operationAmount = sellBuyActivityDto.operationAmount;
        entity.criptoCount = sellBuyActivityDto.criptoCount;
        entity.reputationPoints = 0;
        entity.operationCount = 0;
        entity.type = type;

        return entity;
    }
}
