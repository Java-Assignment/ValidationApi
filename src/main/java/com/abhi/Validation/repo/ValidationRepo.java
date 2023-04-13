package com.abhi.Validation.repo;

import com.abhi.Validation.entity.ValidatedData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRepo extends MongoRepository<ValidatedData,String> {
}
