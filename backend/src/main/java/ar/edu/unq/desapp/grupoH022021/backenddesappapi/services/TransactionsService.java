package ar.edu.unq.desapp.grupoH022021.backenddesappapi.services;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.TransactionDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.SellBuyActivity;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories.SellBuyActivitiesRepository;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories.TransactionsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TransactionsService {
    private TransactionsRepository repository;

    @Autowired
    public TransactionsService(TransactionsRepository repository){
        this.repository = repository;
    }

    public List<Transaction> findByUser(String user) {
        return this.repository.findByUser(user);
    }
    public void create(TransactionDto transaction) {

        Transaction t = new Transaction();
        t.cripto = transaction.cripto;
        t.user = transaction.user;
        t.criptoCount = transaction.criptoCount;
        t.criptoPrice = transaction.criptoPrice;
        t.operationAmount = transaction.operationAmount;
        t.type = transaction.type;

        this.repository.save(t);
    }
    public void confirm(TransactionDto transaction) {

    }

    public void transfer(TransactionDto transaction) {

    }
}
