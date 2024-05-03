package com.example.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.example.entity.*;

@Repository
public interface AccountRepository extends JpaRepository<Account, Integer>
{
    //@Query("SELECT account FROM account WHERE account.username = :username")
    List<Account> findByUsername(String username);
    //List<Account> findByPassword(String password);
}
