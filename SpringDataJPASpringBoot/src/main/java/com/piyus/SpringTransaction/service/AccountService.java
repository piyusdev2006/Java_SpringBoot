package com.piyus.SpringTransaction.service;

import com.piyus.SpringTransaction.model.Account;
import com.piyus.SpringTransaction.repository.AccountRepository;
import org.springframework.stereotype.Service;

@Service
public class AccountService {

    private AccountRepository accountRepository;

    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void createAccount(Account account) {
        accountRepository.save(account);
    }
}
