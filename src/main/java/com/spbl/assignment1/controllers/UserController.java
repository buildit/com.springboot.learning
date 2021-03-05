package com.spbl.assignment1.controllers;

import static java.net.HttpURLConnection.HTTP_BAD_REQUEST;

import java.net.HttpURLConnection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.spbl.assignment1.domain.GenericResponse;
import com.spbl.assignment1.domain.UserRequest;
import com.spbl.assignment1.exception.BadRequestException;
import com.spbl.assignment1.exception.NotFoundException;
import com.spbl.assignment1.model.UserModel;
import com.spbl.assignment1.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;
	
	/**
     * Inserts a new user record
     *
     * @param userRequest
     * @return ResponseEntity
     * @throws BadRequestException
     */
	@PostMapping("/user")
	public ResponseEntity saveUserDetails(@RequestBody final UserRequest userRequest) {
		
		if (userRequest == null) {
            return ResponseEntity.badRequest().build();
        }

        try {
            String userId = userService.saveUserDetails(userRequest);
            return ResponseEntity.ok(
        		GenericResponse
        			.builder()
        			.code(200)
        			.message(userId)
        			.build()
			);
        } catch (BadRequestException bre) {
            return ResponseEntity.badRequest().body(
    			GenericResponse
            		.builder()
            		.code(HTTP_BAD_REQUEST)
                    .message(bre.getMessage())
                    .build()
            );
        }
	}
	
	/**
     * Get a user details
     *
     * @param userId
     * @return ResponseEntity
     * @throws BadRequestException
     * @throws NotFoundException
     */
	@GetMapping("/user/{id}")
	public ResponseEntity getUserDetails(@PathVariable("id") final String userId) {
		
		if (userId == null) {
			return ResponseEntity.badRequest().build();
		}
		
		try {
			return ResponseEntity.ok(userService.getUserDetails(userId));
		} catch (BadRequestException bre) {
			return ResponseEntity.status(HTTP_BAD_REQUEST).build();
		} catch (NotFoundException nfe) {
			return ResponseEntity.notFound().build();
		}
		
	}
	
	/**
     * Update a user details
     *
     * @param userRequest
     * @return ResponseEntity
     * @throws BadRequestException
     * @throws NotFoundException
     */
	@PutMapping("/user") 
	public ResponseEntity updateUserDetails(@RequestBody final UserRequest userRequest) {
		
		if (userRequest == null) {
            return ResponseEntity.badRequest().build();
        }
		
		try {
			userService.updateUserDetails(userRequest);
            return ResponseEntity.ok(
				GenericResponse
					.builder()
					.code(200)
					.message("User updated successfully")
					.build()
    		);
        } catch (BadRequestException bre) {
            return ResponseEntity.badRequest().body(
    			GenericResponse
            		.builder()
            		.code(HTTP_BAD_REQUEST)
                    .message(bre.getMessage())
                    .build()
            );
        } catch (NotFoundException bre) {
            return ResponseEntity.notFound().build();
        }
	}
	
}
