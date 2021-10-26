package ar.edu.unq.desapp.grupoH022021.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.SellBuyActivityDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.UserDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.SellBuyActivity;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.SellBuyActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class SellBuyActivitiesController {

    @Autowired
    private SellBuyActivitiesService activitiesService;

    @GetMapping("/api/activities")
    public List<SellBuyActivity> activities() {
        List<SellBuyActivity> list = activitiesService.findAll();
        return list;
    }

    @PostMapping("/api/activities/buy")
    public void buy(@RequestBody SellBuyActivityDto sellbuyDto) {
        activitiesService.buy(sellbuyDto);
    }

    @PostMapping("/api/activities/sell")
    public void sell(@RequestBody SellBuyActivityDto sellbuyDto) {
        activitiesService.sell(sellbuyDto);
    }
}
