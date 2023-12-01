package com.amazon.userInfo.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.amazon.userInfo.model.UserDetails;

@Repository
public interface UserRepository extends JpaRepository<UserDetails, Integer> {

}
