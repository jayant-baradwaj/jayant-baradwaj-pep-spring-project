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
        List<Account> accExists = accountRepository.findByUsername(account.getUsername());
        if(accExists.size() > 0)
        {
            return null;
        }
        
        return (Account) accountRepository.save(account);
    }

    public Account loginAccount(Account account)
    {
        List<Account> nameExists = accountRepository.findByUsername(account.getUsername());
        if(nameExists.size() == 0)
        {
            return null;
        }

        Account userMatch = nameExists.get(0);
        if(userMatch.getPassword().equals(account.getPassword()))
        {
            return (Account) userMatch;
        }

        return null;
    }
}
