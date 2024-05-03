package com.example.service;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.AccountRepository;
import com.example.entity.Account;

@Service
@Transactional
public class AccountService
{
    private AccountRepository accountRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository)
    {
        this.accountRepository = accountRepository;
    }

    public Account registerAccount(Account account)
    {
        System.out.println(account.toString());

        List<Account> exists = accountRepository.findByUsername(account.getUsername());
        System.out.println(exists.size() + " Size of the list of accounts");
        
        return (Account) accountRepository.save(account);
    }
}
