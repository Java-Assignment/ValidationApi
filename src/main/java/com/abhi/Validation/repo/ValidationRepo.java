package com.abhi.Validation.repo;

import com.abhi.Validation.entity.File;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ValidationRepo extends MongoRepository<File,String> {
}
