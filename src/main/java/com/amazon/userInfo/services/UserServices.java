package com.amazon.userInfo.services;

import org.springframework.http.ResponseEntity;

import com.amazon.userInfo.model.UserDetails;

public interface UserServices {

	public ResponseEntity<String> addUserInfo(UserDetails userDetails);

	public ResponseEntity<UserDetails> getUserInfo(int id);

	public ResponseEntity<UserDetails> updateUserDetails(UserDetails userDetails);

}
