package com.amazon.userInfo.controller;

import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.amazon.userInfo.constants.ApplicationConstant;
import com.amazon.userInfo.model.UserDetails;
import com.amazon.userInfo.services.UserServiceImpl;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserServiceImpl userServiceImpl;

	org.slf4j.Logger logger = LoggerFactory.getLogger(UserController.class);

	@PostMapping
	public ResponseEntity<String> addUserInfo(@RequestBody UserDetails userDetails) {
		try {
			logger.info("Request received for addUserInfo:{}", userDetails);
			ResponseEntity<String> responseEntity = userServiceImpl.addUserInfo(userDetails);
			logger.info("Request processed for addUserInfo:{}", userDetails);
			return responseEntity;
		} catch (Exception e) {
			logger.error("Error processing addUserInfo request", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body(ApplicationConstant.ERROR_WHILE_ADDING_USER);
		}

	}

	@GetMapping("/{id}")
	public ResponseEntity<UserDetails> getUserDetails(@PathVariable int id) {
		try {
			logger.info("Request received for getUserDetailsById:{}", id);
			ResponseEntity<UserDetails> responseEntity = userServiceImpl.getUserInfo(id);
			logger.info("Request processed for getUserDetailsById:{}", id);
			return responseEntity;
		} catch (Exception e) {
			logger.error("Error processing getUserDetailsById request", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}

	@PutMapping("/{id}")
	public ResponseEntity<UserDetails> updateUser(@PathVariable int id, @RequestBody UserDetails updateUserDetails) {
		try {
			logger.info("Request received for updating the user :{}", updateUserDetails);
			ResponseEntity<UserDetails> responseEntity = userServiceImpl.updateUserDetails(updateUserDetails);
			logger.info("Request processed for updating the user :{}", updateUserDetails);
			return responseEntity;
		} catch (Exception e) {
			logger.error("Error processing getUserDetailsById request", e);
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
		}

	}
}
