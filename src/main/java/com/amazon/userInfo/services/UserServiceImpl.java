package com.amazon.userInfo.services;

import java.util.Optional;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.amazon.userInfo.constants.ApplicationConstant;
import com.amazon.userInfo.dao.UserRepository;
import com.amazon.userInfo.model.UserDetails;

@Service
public class UserServiceImpl implements UserServices {

	@Autowired
	private UserRepository userRepository;

	org.slf4j.Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	@Override
	public ResponseEntity<String> addUserInfo(UserDetails userDetails) {
		try {
			if (userDetails.getUserName() == null) {
				logger.info("username is mandatory it should not be null:{}", userDetails);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationConstant.USERNAME_MANDATORY);
			} else if (userDetails.getUserAddress() == null) {
				logger.info("Address is mandatory it should not be null:{}", userDetails);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ApplicationConstant.USERNAME_MANDATORY);
			} else {
				userRepository.save(userDetails);
				return ResponseEntity.ok(ApplicationConstant.USER_ADDED_SUCCESSFULLY);
			}

		} catch (Exception e) {
			logger.error("Exception occurred while storing User Information" + e.getMessage());
			throw e;
		}

	}

	@Override
	public ResponseEntity<UserDetails> getUserInfo(int id) {

		try {
			Optional<UserDetails> userDetails = userRepository.findById(id);
			if (userDetails.isPresent()) {
				return ResponseEntity.ok(userDetails.get());
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

		} catch (Exception e) {
			logger.error("Exception occurred while getting User Information by ID: " + e.getMessage());
			throw e;
		}

	}

	@Override
	public ResponseEntity<UserDetails> updateUserDetails(UserDetails userDetails) {
		try {
			Optional<UserDetails> existingUser = userRepository.findById(userDetails.getUserId());
			if (existingUser.isPresent()) {
				UserDetails currentUser = existingUser.get();
				currentUser.setUserName(userDetails.getUserName());
				currentUser.setUserAddress(userDetails.getUserAddress());
				userRepository.save(currentUser);
				return ResponseEntity.ok(currentUser);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
			}

		} catch (Exception e) {
			logger.error("Exception occurred while updating  User Information : " + e.getMessage());
			throw e;
		}

	}

}
