package ar.edu.unq.desapp.grupoH022021.backenddesappapi.webservices;

import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.SellBuyActivityDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.TransactionDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.dto.UserDto;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.SellBuyActivity;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.model.Transaction;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.SellBuyActivitiesService;
import ar.edu.unq.desapp.grupoH022021.backenddesappapi.services.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class TransactionsController {

    @Autowired
    private TransactionsService transactionsService;

    @GetMapping("/api/transactions/{username}")
    public List<Transaction> transactions(@PathVariable("username") String username) {
        List<Transaction> list = transactionsService.findByUser(username);
        return list;
    }

    @PostMapping("/api/transactions/create")
    public void create(@RequestBody TransactionDto transaction) {
        transactionsService.create(transaction);
    }

    @PostMapping("/api/transactions/confirm")
    public void confirm(@RequestBody TransactionDto transaction) {
        transactionsService.confirm(transaction);
    }

    @PostMapping("/api/transactions/transfer")
    public void transfer(@RequestBody TransactionDto transaction) {
        transactionsService.transfer(transaction);
    }
}
