package com.banco.transactionsMS.controller;

import com.banco.transactionsMS.Repository.AccountRepository;
import com.banco.transactionsMS.model.Account;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/accounts")
public class AccountController {
    @Autowired
    private AccountRepository accountRepository;

    @PostMapping
    public Mono<Account> createAccount(@RequestBody Account account) {
        return accountRepository.save(account);
    }

    @GetMapping("/{accountNumber}")
    public Mono<Account> getAccountByNumber(@PathVariable String accountNumber) {
        return accountRepository.findByAccountNumber(accountNumber);
    }


}
