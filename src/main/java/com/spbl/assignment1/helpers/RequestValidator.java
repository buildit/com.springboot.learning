package com.spbl.assignment1.helpers;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;

import com.spbl.assignment1.domain.UserRequest;

@Component
public class RequestValidator {

	private static final String RFC5322_EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";
	private static final int ALLOWED_NAME_LENGTH = 3;
	
	/**
     * Validates if email conforms to RFC5322
     *
     * @param email String
     * @return true | false
     */
    public static boolean isEmailValid(final String email) {
        final Pattern emailPattern = Pattern.compile(RFC5322_EMAIL_REGEX);
        Matcher matcher = emailPattern.matcher(email);
        if (!matcher.matches()) {
            return false;
        }
        return true;
    }
    
    /**
     * Validates if user details are valid or not
     *
     * @param userRequest
     * @return true | false
     */
    public static boolean isUserRequestValid(final UserRequest userRequest) {
    	boolean isValid = false;
    	
    	if (userRequest.getEmail() == null || !isEmailValid(userRequest.getEmail())) {
    		return isValid;
    	}
    	
    	if (userRequest.getFirstName() == null || userRequest.getFirstName().trim().length() < ALLOWED_NAME_LENGTH) {
    		return isValid;
    	}
    	
    	if (userRequest.getMobile() == null) {
    		return isValid;
    	}    	
    	
    	isValid = true;
    	
    	return isValid;
    }
}
