package com.example.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.RequestEntity.BodyBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.service.AccountService;
import com.example.service.MessageService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.example.entity.*;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */

@RestController
//@RequestMapping()
public class SocialMediaController
{
    private AccountService accountService;
    private MessageService messageService;

    public SocialMediaController(AccountService accountService, MessageService messageService) 
    {
        this.accountService = accountService;
        this.messageService = messageService;
    }

    @PostMapping("/register")
    public ResponseEntity<?> registerAccount(@RequestBody Account account)
    {
        //ObjectMapper om = new ObjectMapper();
        if(account.getUsername().equals("") || account.getPassword().length() < 4)
        {
            return ResponseEntity.status(400).body(null);
        }
        
        System.out.println("RUNNING ACCOUNT SERVICE");
        Account registered = accountService.registerAccount(account);
        System.out.println("END OF ACCOUNT SERVICE RUN");

        if(registered != null)
        {
            return ResponseEntity.status(200).body(registered.toString());
        }

        return ResponseEntity.status(409).body(null);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginAccount(@RequestBody Account account)
    {
        System.out.println("RUNNING LOGIN FROM CONTROLLER");
        Account login = accountService.loginAccount(account);
        System.out.println("END OF LOGIN FROM SERVICE");

        if(login != null)
        {
            return ResponseEntity.status(200).body(login.toString());
        }

        return ResponseEntity.status(400).body(null);
    }
}
