package com.spbl.assignment1.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Document(collection = "users")
public class UserModel {
	
	@Id
    private ObjectId id;
    private String firstName;
    private String lastName;
    private String gender;
    private Date dateOfBirth;
    private String email;
    private String mobile;
    
}
