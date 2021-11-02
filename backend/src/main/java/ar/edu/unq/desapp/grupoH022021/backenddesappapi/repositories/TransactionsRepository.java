package ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.SellBuyActivity;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.User;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TransactionsRepository extends CrudRepository<Transaction, Integer> {
    List<Transaction> findByUser(String user);
}
