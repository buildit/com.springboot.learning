package com.spbl.assignment1.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.spbl.assignment1.domain.UserRequest;
import com.spbl.assignment1.exception.BadRequestException;
import com.spbl.assignment1.model.UserModel;
import com.spbl.assignment1.repository.UserRepository;

@Component
public class UserDao {
	
	@Autowired
	private UserRepository userRepository;
	
	public Optional<UserModel> findByEmail(final String email) {
		return userRepository.findByEmail(email);
	}
	
	public Optional<UserModel> findByMobile(final String mobile) {
		return userRepository.findByMobile(mobile);
	}
	
	public Optional<UserModel> findByUserId(final String userId) {
		return userRepository.findById(userId);
	}
	
	public UserModel save(final UserModel userModel) {
		return userRepository.save(userModel);
	}
	
	public UserModel update(final UserModel updatedUser, final UserRequest userRequest) throws BadRequestException {
		
		try {
			updatedUser.setDateOfBirth(userRequest.getDateOfBirth());
			updatedUser.setEmail(userRequest.getEmail());
			updatedUser.setFirstName(userRequest.getFirstName());
			updatedUser.setLastName(userRequest.getLastName());
			updatedUser.setGender(userRequest.getGender());
			updatedUser.setMobile(userRequest.getMobile());
			userRepository.save(updatedUser);
			return updatedUser;
		} catch(Exception e) {
			throw new BadRequestException("Error saving user details. Please try again");
		}
	}
}
