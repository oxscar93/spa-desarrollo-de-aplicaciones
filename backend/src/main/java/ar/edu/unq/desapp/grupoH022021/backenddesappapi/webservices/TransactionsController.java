package ar.edu.unq.desapp.grupoH022021.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.aspects.Log;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.TransactionDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.TransactionStatusDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @Log
    @GetMapping("/api/transactions/{username}")
    public List<Transaction> transactions(@PathVariable("username") String username) {
        List<Transaction> list = transactionsService.findByUser(username);
        return list;
    }

    @Log
    @GetMapping("/api/transactions/status/{id}")
    public TransactionStatusDto transactions(@PathVariable("id") Integer id) {
        return transactionsService.findStatus(id);
    }

    @Log
    @PostMapping("/api/transactions/create")
    public TransactionDto create(@RequestBody TransactionDto transaction) {
        return transactionsService.create(transaction);
    }

    @Log
    @PostMapping("/api/transactions/confirm")
    public void confirm(@RequestBody TransactionDto transaction) {
        transactionsService.confirm(transaction);
    }

    @Log
    @PostMapping("/api/transactions/cancel")
    public void cancel(@RequestBody TransactionDto transaction) {
        transactionsService.cancel(transaction);
    }
}
