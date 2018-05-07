package com.reviews.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reviews.app.models.TestingDisplay;

@Repository
public interface ReviewRepository extends MongoRepository<TestingDisplay, String> {

}