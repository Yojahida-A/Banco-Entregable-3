package com.banco.transactionsMS.service;

import com.banco.transactionsMS.Repository.TransactionRepository;
import com.banco.transactionsMS.model.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    public Mono<Transaction> createTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }

    public Flux<Transaction> getTransactionsBySourceAccount(String accountId) {
        return transactionRepository.findBySourceAccount(accountId);
    }

    public Mono<Transaction> getTransactionById(String id) {
        return transactionRepository.findById(id);
    }

    public Mono<Void> deleteTransaction(String id) {
        return transactionRepository.deleteById(id);
    }
    public Flux<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
    }

}
