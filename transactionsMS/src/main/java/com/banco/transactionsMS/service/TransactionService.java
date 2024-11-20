package com.banco.transactionsMS.service;

import com.banco.transactionsMS.Repository.AccountRepository;
import com.banco.transactionsMS.Repository.TransactionRepository;
import com.banco.transactionsMS.model.Account;
import com.banco.transactionsMS.model.Transaction;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class TransactionService {

    private final TransactionRepository transactionRepository;
    private final AccountService accountService;

    public Mono<Transaction> deposit(String account, double amount) {
        return accountService.findByAccountNumber(account)
                .flatMap(accountData -> {
                    accountData.setBalance(accountData.getBalance() + amount);
                    return accountService.save(accountData)
                            .then(createTransaction("Depósito", amount, account, null));
                });
    }

    public Mono<Transaction> withdraw(String account, double amount) {
        return accountService.findByAccountNumber(account)
                .flatMap(accountData -> {
                    if (accountData.getBalance() < amount) {
                        return Mono.error(new IllegalArgumentException("Saldo insuficiente"));
                    }
                    accountData.setBalance(accountData.getBalance() - amount);
                    return accountService.save(accountData)
                            .then(createTransaction("Retiro", amount, account, null));
                });
    }

    public Mono<Transaction> transfer(String accountOrigin, String accountDestination, double amount) {
        return accountService.findByAccountNumber(accountOrigin)
                .flatMap(accountDataOrigin -> {
                    if (accountDataOrigin.getBalance() < amount) {
                        return Mono.error(new IllegalArgumentException("Saldo insuficiente"));
                    }
                    accountDataOrigin.setBalance(accountDataOrigin.getBalance() - amount);
                    return accountService.save(accountDataOrigin)
                            .then(accountService.findByAccountNumber(accountDestination))
                            .flatMap(accountDataDestination -> {
                                accountDataDestination.setBalance(accountDataDestination.getBalance() + amount);
                                return accountService.save(accountDataDestination)
                                        .then(createTransaction("Transferencia", amount, accountOrigin, accountDestination));
                            });
                });
    }

    private Mono<Transaction> createTransaction(String type, double amount, String accountOrigin, String accountDestination) {
        return transactionRepository.save(
                Transaction.builder()
                        .type(type)
                        .amount(amount)
                        .date(LocalDateTime.now())
                        .accountOrigin(accountOrigin)
                        .accountDestination(accountDestination)
                        .build()
        );
    }



    public Flux<Transaction> getAllTransactions() {
        return transactionRepository.findAll();
        }


}