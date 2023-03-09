package com.chatgptswitcherapi.chatgptswitcherapi.repository;

import com.chatgptswitcherapi.chatgptswitcherapi.entity.RequestDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RequestDetailRepository extends JpaRepository<RequestDetail,Long> {
     public List<RequestDetail> findByUserId(int id);



}
