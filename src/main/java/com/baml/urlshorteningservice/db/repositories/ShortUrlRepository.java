package com.baml.urlshorteningservice.db.repositories;

import com.baml.urlshorteningservice.db.model.ShortUrlDetails;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ShortUrlRepository extends MongoRepository<ShortUrlDetails, String> {
}
