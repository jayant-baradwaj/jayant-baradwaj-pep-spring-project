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
        //System.out.println(account.toString());

        List<Account> accExists = accountRepository.findByUsername(account.getUsername());
        //System.out.println(accExists.size());
        if(accExists.size() > 0)
        {
            return null;
        }
        
        return (Account) accountRepository.save(account);
    }

    public Account loginAccount(Account account)
    {
       //System.out.println(account.toString());

        List<Account> nameExists = accountRepository.findByUsername(account.getUsername());
        //System.out.println(nameExists.size());
        if(nameExists.size() == 0)
        {
            return null;
        }
        Account userMatch = nameExists.get(0);
        //System.out.println(userMatch.toString());
        //System.out.println("Given Password: " + account.getPassword());

        //System.out.println("Found Password: " + userMatch.getPassword());
        if(userMatch.getPassword().equals(account.getPassword()))
        {
            return (Account) userMatch;
        }

        //System.out.println("The passwrods didn't match");
        return null;
    }
}
