package com.reviews.app.repositories;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.reviews.app.models.OutputTable;

@Repository
public interface ReviewRepository extends MongoRepository<OutputTable, String> {

}