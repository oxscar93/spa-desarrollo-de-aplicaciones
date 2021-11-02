package ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Transaction;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransactionsRepository extends CrudRepository<Transaction, Integer> {
    List<Transaction> findByUser(String user);
}
