package com.jvt.account.service;

import com.jvt.account.entity.Account;
import com.jvt.account.exception.AccountNotFoundException;
import com.jvt.account.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.List;

@Service
public class AccountService {

    private final AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public Account getAccountById(Long accountId){
        return accountRepository.findById(accountId).orElseThrow(
                () -> new AccountNotFoundException("account not found")
        );
    }

    public Long createAccount(String name, String email, String phone, List<Long> bills){
        Account account = new Account(name, email, phone, OffsetDateTime.now(), bills);
        return accountRepository.save(account).getAccountId();
    }

    public Account updateAccount(Long accountId, String name,
                                 String email, String phone, List<Long> bills){
        Account account = new Account(accountId, name, email, phone, bills);

        return accountRepository.save(account);
    }

    public Account deleteAccount(Long accountId){
        Account deletedAccount = getAccountById(accountId);
        accountRepository.deleteById(accountId);
        return deletedAccount;
    }
}
