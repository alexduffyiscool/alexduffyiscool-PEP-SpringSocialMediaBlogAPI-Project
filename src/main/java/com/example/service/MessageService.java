package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.example.entity.Message;
import com.example.repository.AccountRepository;
import com.example.repository.MessageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import java.util.Optional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
  @Autowired
  private MessageRepository messageRepository;

  @Autowired
  private AccountRepository accountRepository;

  public Optional<Message> createMessage(Message message)
  {
    if (message.getMessageText() == null || message.getMessageText().isBlank() || message.getMessageText().length() > 255 || !accountRepository.existsById(message.getPostedBy()))
    {
      return Optional.empty();
    }
    else
    {
      return Optional.of(messageRepository.save(message));
    }
  }

  public List<Message> getAllMessages()
  {
    return messageRepository.findAll();
  }

  public Optional<Message> getMessageById(Integer id)
  {
    return messageRepository.findById(id);
  }

  public boolean deleteMessage(Integer id)
  {
    if (messageRepository.existsById(id))
    {
      messageRepository.deleteById(id);
      return true;
    }
    return false;
  }

  public boolean updateMessage(Integer id, String text)
  {
    Optional<Message> optional = messageRepository.findById(id);
    if (optional.isEmpty() || text == null || text.isBlank() || text.length() > 255)
    {
      return false;
    }

    Message message = optional.get();
    message.setMessageText(text);
    messageRepository.save(message);
    return true;
  }

  public List<Message> getMessagesByUser(Integer accountID)
  {
    return messageRepository.findByPostedBy(accountID);
  }
}
