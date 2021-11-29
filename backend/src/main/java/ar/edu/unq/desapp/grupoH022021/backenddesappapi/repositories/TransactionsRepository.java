package ar.edu.unq.desapp.grupoH022021.backenddesappapi.repositories;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Transaction;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TransactionsRepository extends CrudRepository<Transaction, Integer> {
    List<Transaction> findByUser(String user);
}
