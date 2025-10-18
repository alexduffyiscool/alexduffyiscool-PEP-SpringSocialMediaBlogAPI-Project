package com.example.controller;

import org.apache.catalina.connector.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import java.util.Optional;
import java.util.List;
import com.example.entity.Account;
import com.example.entity.Message;
import com.example.service.AccountService;
import com.example.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.example.service.MessageService;

/**
 * TODO: You will need to write your own endpoints and handlers for your controller using Spring. The endpoints you will need can be
 * found in readme.md as well as the test cases. You be required to use the @GET/POST/PUT/DELETE/etc Mapping annotations
 * where applicable as well as the @ResponseBody and @PathVariable annotations. You should
 * refer to prior mini-project labs and lecture materials for guidance on how a controller may be built.
 */
@RestController
public class SocialMediaController {
  @Autowired
  private AccountService accountService;

  @Autowired 
  private MessageService messageService;

  @PostMapping("/register")
  public ResponseEntity<?> register(@RequestBody Account account)
  {
    Optional<Account> result = accountService.register(account);
    
    if (result.isPresent() && result.get().getAccountId() == -1)
    {
      return ResponseEntity.status(409).build();
    }
    else if (result.isEmpty())
    {
      return ResponseEntity.status(400).build();
    }
    if (result.get().getAccountId() == -1)
    {
      return ResponseEntity.status(409).build();
    }
    else
    {
      return ResponseEntity.ok(result.get());
    }
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody Account account)
  {
    Optional<Account> result = accountService.login(account.getUsername(), account.getPassword());
    return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
  }

  @PostMapping("/messages")
  public ResponseEntity<?> createMessage(@RequestBody Message message)
  {
    Optional<Message> result = messageService.createMessage(message);
    return result.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.badRequest().build());
  }

  @GetMapping("/messages")
  public List<Message> getAllMessages()
  {
    return messageService.getAllMessages();
  }

  @GetMapping("/messages/{messageId}")
  public ResponseEntity<?> getMessageById(@PathVariable("messageId") Integer messageId)
  {
    Optional<Message> result = messageService.getMessageById(messageId);
    return ResponseEntity.ok(result.orElse(null));
  }

  @DeleteMapping("/messages/{messageId}")
  public ResponseEntity<?> deleteMessage(@PathVariable Integer messageId)
  {
    boolean deleted = messageService.deleteMessage(messageId);
    if (deleted) return ResponseEntity.ok(1);
    return ResponseEntity.ok().build();
  }

  @PatchMapping("/messages/{messageId}")
  public ResponseEntity<?> updateMessage(@PathVariable Integer messageId, @RequestBody Message newMessage)
  {
    boolean updated = messageService.updateMessage(messageId, newMessage.getMessageText());
    if (!updated) return ResponseEntity.badRequest().build();
    return ResponseEntity.ok(1);
  }

  @GetMapping("/accounts/{accountId}/messages")
  public ResponseEntity<List<Message>> getMessagesByUser(@PathVariable Integer accountId)
  {
    List<Message> messages = messageService.getMessagesByAccountId(accountId);
    return ResponseEntity.ok(messages);
  }
}
