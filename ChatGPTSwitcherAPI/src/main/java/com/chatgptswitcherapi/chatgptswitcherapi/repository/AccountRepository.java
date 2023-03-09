package com.chatgptswitcherapi.chatgptswitcherapi.repository;

import com.chatgptswitcherapi.chatgptswitcherapi.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccountRepository  extends JpaRepository<Account,Long> {


   public void deleteByAccountKey(String AccountKey);

  public List<Account> findAllByAccountLimit(boolean limit);
}
