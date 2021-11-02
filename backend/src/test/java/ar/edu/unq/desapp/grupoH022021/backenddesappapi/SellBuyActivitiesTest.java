package ar.edu.unq.desapp.grupoH022021.backenddesappapi;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.SellBuyActivityDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.UserDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.SellBuyActivity;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.SellBuyActivitiesService;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class SellBuyActivitiesTest {
    @Autowired
    private SellBuyActivitiesService sellBuyActivitiesService;

    public SellBuyActivitiesTest(){

    }

    @Test
    public void setBuyActivity() {
        SellBuyActivityDto s = new SellBuyActivityDto();
        s.criptoPrice = 10.9;
        s.criptoCount = 10;
        s.cripto = "asd";
        s.operationAmount = 100.0;
        s.user = "test";
        s.dateTime = Instant.now();

        sellBuyActivitiesService.buy(s);
        List<SellBuyActivity> result = sellBuyActivitiesService.findAll();

		assertTrue("Activity not found", result != null);
    }

    @Test
    public void setSellActivity() {
        SellBuyActivityDto s = new SellBuyActivityDto();
        s.criptoPrice = 10.9;
        s.criptoCount = 10;
        s.cripto = "asd";
        s.operationAmount = 100.0;
        s.user = "test";
        s.dateTime = Instant.now();

        sellBuyActivitiesService.sell(s);
        List<SellBuyActivity> result = sellBuyActivitiesService.findAll();


        assertTrue("Activity not found", result != null);
    }
}
