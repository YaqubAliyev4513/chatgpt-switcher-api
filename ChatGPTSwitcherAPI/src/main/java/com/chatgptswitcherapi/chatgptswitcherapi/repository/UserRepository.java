package com.chatgptswitcherapi.chatgptswitcherapi.repository;

import com.chatgptswitcherapi.chatgptswitcherapi.entity.User;
import com.chatgptswitcherapi.chatgptswitcherapi.request.UserRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    public List<User> findAllByOnline(boolean online);

    public User findByUsername(String username);

    @Modifying
    @Query(value = "UPDATE user SET refers = ?1 WHERE id = ?2", nativeQuery = true)
    public  void  updateUserReferalCount(int count, int id);

    @Modifying
    @Query(value = "UPDATE user SET level = ?1,talk = ?2 WHERE id = ?3", nativeQuery = true)
    public  void  updateUserLevelAndTalk(String level,int talk, int id);

    @Modifying
    @Query(value = "UPDATE user SET talk = ?1 WHERE id = ?2", nativeQuery = true)
    public  void  updateUserTalk(int talk, int id);

    @Modifying
    @Query(value = "UPDATE user SET last_use_time = ?1 WHERE id = ?2", nativeQuery = true)
    public  void  updateUserLastUseTime(LocalDateTime lastUseTime, int id);

    @Modifying
    @Query(value = "UPDATE user SET tokens_used = ?1 WHERE id = ?2", nativeQuery = true)
    public  void  updateUserTokensUsed(int tokens_used, int id);


    @Modifying
    @Query(value = "UPDATE user SET online = ?1 WHERE id = ?2", nativeQuery = true)
    public void  loginCondition(boolean login,int id);
}
