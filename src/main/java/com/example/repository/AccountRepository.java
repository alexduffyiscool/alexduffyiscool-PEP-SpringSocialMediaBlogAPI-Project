package com.example.repository;

@Respository
public interface AccountRepository {
  Optional<Account> findByUsername(String username);
  Optional<Account> findByUsernameAndPassword(String username, String password);

}
