package com.banco.transactionsMS.service;

import com.banco.transactionsMS.Repository.AccountRepository;
import com.banco.transactionsMS.model.Account;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@RequiredArgsConstructor

public class AccountService {

    private final AccountRepository accountRepository;

    public Mono<Account> findByAccountNumber(String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }

    public Mono<Account> save(Account account) {
        return accountRepository.save(account);
    }
}
