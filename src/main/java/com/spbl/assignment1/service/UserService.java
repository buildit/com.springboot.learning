package com.spbl.assignment1.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.spbl.assignment1.dao.UserDao;
import com.spbl.assignment1.domain.UserRequest;
import com.spbl.assignment1.domain.UserResponse;
import com.spbl.assignment1.exception.BadRequestException;
import com.spbl.assignment1.exception.NotFoundException;
import com.spbl.assignment1.helpers.RequestValidator;
import com.spbl.assignment1.model.UserModel;

@Service
public class UserService {
	
	@Autowired
	private UserDao userDao;
	
	@Autowired
	private ObjectMapper objectMapper;
	
	public String saveUserDetails(final UserRequest userRequest) throws BadRequestException {
		
		if (userRequest == null || !RequestValidator.isUserRequestValid(userRequest)) {
			throw new BadRequestException();
		}
		
		Optional<UserModel> user = userDao.findByEmail(userRequest.getEmail());
		if (user.isPresent()) {
			throw new BadRequestException("Email already exists. Please try again");
		}
		
		user = userDao.findByMobile(userRequest.getMobile());
		
		if (user.isPresent()) {
			throw new BadRequestException("Mobile Number already exists. Please try again");
		}
		
		user = Optional.ofNullable(UserModel
			.builder()
			.firstName(userRequest.getFirstName())
			.lastName(userRequest.getLastName())
			.gender(userRequest.getGender())
			.email(userRequest.getEmail())
			.mobile(userRequest.getMobile())
			.dateOfBirth(userRequest.getDateOfBirth())
			.build());
		
		userDao.save(user.get());
		
		return user.get().getId().toHexString();
	}
	
	public UserResponse getUserDetails(final String userId) throws BadRequestException, NotFoundException {
		
		Optional<UserModel> user = userDao.findByUserId(userId);
		
		if (!user.isPresent()) {
			throw new NotFoundException();
		}
		
		UserResponse userResponse = objectMapper.convertValue(user.get(), UserResponse.class);
		userResponse.setUserId(user.get().getId().toHexString());
		return userResponse;
	}
	
	public void updateUserDetails(final UserRequest userRequest) throws NotFoundException, BadRequestException {
		
		if (userRequest == null || !RequestValidator.isUserRequestValid(userRequest)) {
			throw new BadRequestException();
		}
		
		Optional<UserModel> user = userDao.findByUserId(userRequest.getId());
		if (!user.isPresent()) {
			throw new BadRequestException("User ID does not exist. Please try again");
		}
		
		
		try {
			userDao.update(user.get(), userRequest);
		} catch (BadRequestException bre) {
			throw new BadRequestException(bre.getMessage());
		}
	}
	
}
