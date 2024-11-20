package com.banco.transactionsMS.controller;

import com.banco.transactionsMS.model.Transaction;
import com.banco.transactionsMS.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;


@RestController
@RequestMapping("/transactions")
public class TransactionController {

    @Autowired
    private TransactionService transactionService;

    @PostMapping("/deposit")
    public Mono<Transaction> deposit(@RequestBody Transaction transaction) {
        transaction.setType("Deposit");
        return transactionService.createTransaction(transaction);
    }

    @PostMapping("/withdrawal")
    public Mono<Transaction> withdrawal(@RequestBody Transaction transaction) {
        transaction.setType("Withdrawal");
        return transactionService.createTransaction(transaction);
    }

    @PostMapping("/transfer")
    public Mono<Transaction> transfer(@RequestBody Transaction transaction) {
        transaction.setType("Transfer");
        return transactionService.createTransaction(transaction);
    }

    @GetMapping
    public Flux<Transaction> getAllTransactions() {
        return transactionService.getAllTransactions();
    }

    @GetMapping("/{id}")
    public Mono<Transaction> getTransactionById(@PathVariable String id) {
        return transactionService.getTransactionById(id);
    }

    @GetMapping("/account/{accountId}")
    public Flux<Transaction> getTransactionsBySourceAccount(@PathVariable String accountId) {
        return transactionService.getTransactionsBySourceAccount(accountId);
    }

    @DeleteMapping("/{id}")
    public Mono<Void> deleteTransaction(@PathVariable String id) {
        return transactionService.deleteTransaction(id);
    }


}
