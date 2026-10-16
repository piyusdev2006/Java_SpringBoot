package com.piyus.SpringTransaction.controller;

import com.piyus.SpringTransaction.model.Account;
import com.piyus.SpringTransaction.service.AccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/account")
public class AccountController {

    AccountService accountService;

    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }

    @PostMapping
    public ResponseEntity<String> createAccount(
            @RequestBody Account account) {

        accountService.createAccount(account);
        return ResponseEntity.ok("DONE");
    }
}