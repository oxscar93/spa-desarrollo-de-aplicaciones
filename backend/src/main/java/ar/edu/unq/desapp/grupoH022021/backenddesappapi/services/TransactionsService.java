package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.TransactionDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.TransactionStatusDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.SellBuyActivity;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.User;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories.SellBuyActivitiesRepository;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Service
public class TransactionsService {
    private TransactionsRepository repository;
    private SellBuyActivitiesRepository sellBuyRepository;

    @Autowired
    public TransactionsService(TransactionsRepository repository, SellBuyActivitiesRepository sellBuyRepository){
        this.repository = repository;
        this.sellBuyRepository = sellBuyRepository;
    }

    public List<Transaction> findByUser(String user) {
        return this.repository.findByUser(user);
    }

    public TransactionStatusDto findStatus(Integer id) {
        Transaction t = this.repository.findById(id).get();

        TransactionStatusDto result = new TransactionStatusDto();
        result.status = t.status;

        return result;
    }
    public TransactionDto create(TransactionDto transaction) {
        Transaction t = new Transaction();

        t.activityId = transaction.activityId;
        t.cripto = transaction.cripto;
        t.user = transaction.user;
        t.criptoCount = transaction.criptoCount;
        t.criptoPrice = transaction.criptoPrice;
        t.operationAmount = transaction.operationAmount;
        t.type = transaction.type;
        t.status = transaction.status;
        t.startedDate = LocalDateTime.now();

        Transaction saved = this.repository.save(t);

        TransactionDto result = new TransactionDto();

        result.id = saved.id;
        result.activityId = saved.activityId;

        return result;
    }
    public void confirm(TransactionDto transaction) {

        Transaction t = this.repository.findById(transaction.id).get();
        SellBuyActivity s = this.sellBuyRepository.findById(transaction.activityId).get();

        t.status = 2; //confirmed;
        s.operationCount = s.operationCount + 1;
        s.reputationPoints = getPointsBasedOnTiming(t) / (s.operationCount);
        s.criptoCount = s.criptoCount - transaction.criptoCount;

        this.sellBuyRepository.save(s);
        this.repository.save(t);
    }

    public void cancel(TransactionDto transaction) {
        SellBuyActivity s = this.sellBuyRepository.findById(transaction.activityId).get();

        if (this.repository.findById(transaction.id).isPresent()){
            Transaction t = this.repository.findById(transaction.id).get();
            t.status = 3; //cancelled;
            this.repository.save(t);
        }

        s.reputationPoints = s.reputationPoints - 20;

        this.sellBuyRepository.save(s);

    }

    private int getPointsBasedOnTiming(Transaction t){
        LocalDateTime now = LocalDateTime.now();

        long minutesPassed = ChronoUnit.MINUTES.between(now, t.startedDate);

        if (minutesPassed > 30){
            return 5;
        }

        return 10;
    }
}
