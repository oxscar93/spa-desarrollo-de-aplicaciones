package ar.edu.unq.desapp.grupoH022021.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.SellBuyActivity;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.SellBuyActivitiesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
}
