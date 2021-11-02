package ar.edu.unq.desapp.grupoH022021.backenddesappapi;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.SellBuyActivityDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.TransactionDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.TransactionStatusDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.SellBuyActivity;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.SellBuyActivitiesService;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.TransactionsService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.Instant;
import java.util.List;

import static org.springframework.test.util.AssertionErrors.assertTrue;

@SpringBootTest
public class TransactionTests {
    @Autowired
    private SellBuyActivitiesService sellBuyActivitiesService;
    @Autowired
    private TransactionsService transactionsService;

    public TransactionTests(){

    }

    @Test
    public void createTransaction() {
        TransactionDto t = new TransactionDto();
        t.criptoPrice = 10;
        t.criptoCount = 10;
        t.cripto = "asd";
        t.operationAmount = 100;
        t.user = "test";
        t.activityId = 1;
        t.status = 1;

        transactionsService.create(t);
        List<Transaction> result = transactionsService.findByUser("test");

		assertTrue("Transaction not found", result.size() != 0);
    }

    @Test
    public void confirmPendingSellTransaction() {
        SellBuyActivityDto s = new SellBuyActivityDto();
        s.criptoPrice = 10.9;
        s.criptoCount = 10;
        s.cripto = "asd";
        s.operationAmount = 100.0;
        s.user = "test";
        s.dateTime = Instant.now();

        SellBuyActivity r = sellBuyActivitiesService.sell(s);

        TransactionDto t = new TransactionDto();
        t.criptoPrice = 10;
        t.criptoCount = 10;
        t.cripto = "asd";
        t.operationAmount = 100;
        t.user = "test";
        t.activityId = r.id;
        t.status = 1;

        TransactionDto result = transactionsService.create(t);

        t.id = result.id;

        transactionsService.confirm(t);

        TransactionStatusDto ts = transactionsService.findStatus(result.id);

        assertTrue("Activity not found", ts.status != 1);
    }

    @Test
    public void confirmPendingBuyTransaction() {
        SellBuyActivityDto s = new SellBuyActivityDto();
        s.criptoPrice = 10.9;
        s.criptoCount = 10;
        s.cripto = "asd";
        s.operationAmount = 100.0;
        s.user = "test";
        s.dateTime = Instant.now();

        SellBuyActivity r = sellBuyActivitiesService.buy(s);

        TransactionDto t = new TransactionDto();
        t.criptoPrice = 10;
        t.criptoCount = 10;
        t.cripto = "asd";
        t.operationAmount = 100;
        t.user = "test";
        t.activityId = r.id;
        t.status = 1;

        TransactionDto result = transactionsService.create(t);

        t.id = result.id;

        transactionsService.confirm(t);

        TransactionStatusDto ts = transactionsService.findStatus(result.id);

        assertTrue("Activity not found", ts.status != 1);
    }

    @Test
    public void checkSuccessTransactionReputationPoints() {

        SellBuyActivityDto s = new SellBuyActivityDto();
        s.criptoPrice = 10.9;
        s.criptoCount = 10;
        s.cripto = "asd";
        s.operationAmount = 100.0;
        s.user = "test";
        s.dateTime = Instant.now();

        SellBuyActivity r = sellBuyActivitiesService.buy(s);

        TransactionDto t = new TransactionDto();
        t.criptoPrice = 10;
        t.criptoCount = 10;
        t.cripto = "asd";
        t.operationAmount = 100;
        t.user = "test";
        t.activityId = r.id;
        t.status = 1;

        TransactionDto result = transactionsService.create(t);

        t.id = result.id;

        transactionsService.confirm(t);

        TransactionStatusDto ts = transactionsService.findStatus(result.id);
        SellBuyActivity res = sellBuyActivitiesService.findByID(r.id);

        assertTrue("Status invalid", ts.status == 2);
        assertTrue("Incorrect Points", res.reputationPoints == 10);
    }

    @Test
    public void checkCancelledTransactionReputationPoints() {

        SellBuyActivityDto s = new SellBuyActivityDto();
        s.criptoPrice = 10.9;
        s.criptoCount = 10;
        s.cripto = "asd";
        s.operationAmount = 100.0;
        s.user = "test";
        s.dateTime = Instant.now();

        SellBuyActivity r = sellBuyActivitiesService.buy(s);

        TransactionDto t = new TransactionDto();
        t.criptoPrice = 10;
        t.criptoCount = 10;
        t.cripto = "asd";
        t.operationAmount = 100;
        t.user = "test";
        t.activityId = r.id;
        t.status = 1;

        TransactionDto result = transactionsService.create(t);

        t.id = result.id;

        transactionsService.cancel(t);

        TransactionStatusDto ts = transactionsService.findStatus(result.id);
        SellBuyActivity res = sellBuyActivitiesService.findByID(r.id);

        assertTrue("Status invalid", ts.status == 3);
        assertTrue("Incorrect Points", res.reputationPoints == -20);
    }
}
