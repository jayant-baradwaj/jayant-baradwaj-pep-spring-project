package com.example.service;

import java.util.Optional;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.repository.*;
import com.example.entity.*;

@Service
@Transactional
public class MessageService
{
    private AccountRepository accountRepository;
    private MessageRepository messageRepository;

    @Autowired
    public MessageService(MessageRepository messageRepository, AccountRepository accountRepository)
    {
        this.messageRepository = messageRepository;
        this.accountRepository = accountRepository;
    }

    public Message createMessage(Message message)
    {
        Optional<Account> accExists = accountRepository.findById(message.getPostedBy());
        if(accExists.isPresent())
        {
            return (Message) messageRepository.save(message);
            
        }

        return null;
    }

    public List<Message> getAllMessages()
    {
        return messageRepository.findAll();
    }

    public Message getMessageByMessageId(int message_id)
    {
        Optional<Message> msgExists = messageRepository.findById(message_id);
        if(msgExists.isPresent())
        {
            return (Message) msgExists.get();
        }

        return null;
    }

    public int deleteMessageByMessageId(int message_id)
    {
        Optional<Message> msgExists = messageRepository.findById(message_id);
        if(msgExists.isPresent())
        {
            messageRepository.deleteById(message_id);
            return 1;
        }
        
        return 0;
    }

    public int updateMessage(int message_id, String message_text)
    {
        Optional<Message> msgExists = messageRepository.findById(message_id);
        if(msgExists.isPresent())
        {
            Message message = msgExists.get();
            message.setMessageText(message_text);
            messageRepository.save(message);
            return 1;
        }

        return 0;
    }

    public List<Message> getMessagesByAccountId(int account_id)
    {
        return messageRepository.findByPostedBy(account_id);
    }
}
