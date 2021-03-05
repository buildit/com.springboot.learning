package com.spbl.assignment1.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import com.spbl.assignment1.model.UserModel;

@Repository
public interface UserRepository extends MongoRepository<UserModel, String> {

	@Query("{ 'email' : ?0 }")
    Optional<UserModel> findByEmail(String email);

	@Query("{ 'mobile' : ?0 }")
    Optional<UserModel> findByMobile(String mobile);

	
}
