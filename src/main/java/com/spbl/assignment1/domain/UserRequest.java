package com.spbl.assignment1.domain;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRequest {

	private String id;
	private String email;
    private String firstName;
    private String lastName;
    private String mobile;
    private String gender;
    private Date dateOfBirth;
    
}
