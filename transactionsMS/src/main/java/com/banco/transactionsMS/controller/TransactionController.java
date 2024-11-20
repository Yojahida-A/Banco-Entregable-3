package com.banco.transactionsMS.controller;

import com.banco.transactionsMS.model.Transaction;
import com.banco.transactionsMS.service.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/transactions")
@RequiredArgsConstructor
public class TransactionController {

    private final TransactionService transactionService;

    @PostMapping("/deposito")
    public Mono<Transaction> deposit(@RequestParam String account, @RequestParam double amount) {
        return transactionService.deposit(account, amount);
    }

    @PostMapping("/retiro")
    public Mono<Transaction> withdraw(@RequestParam String account, @RequestParam double amount) {
        return transactionService.withdraw(account, amount);
    }

    @PostMapping("/transferencia")
    public Mono<Transaction> transfer(@RequestParam String accountOrigin, @RequestParam String accountDestination, @RequestParam double amount) {
        return transactionService.transfer(accountOrigin, accountDestination, amount);
    }

    @GetMapping("/history")
    public Flux<Transaction> getTransactionHistory() {
        return transactionService.getAllTransactions();
    }




}
