package com.baml.urlshorteningservice.db.service;

import com.baml.urlshorteningservice.db.model.ShortUrlDetails;
import com.baml.urlshorteningservice.db.repositories.ShortUrlRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ShortUrlService {

    private ShortUrlRepository shortUrlRepository;
    @Autowired
    public ShortUrlService(ShortUrlRepository shortUrlRepository){
        this.shortUrlRepository = shortUrlRepository;
    }

    @CachePut(value = "shortUrlDetails", key = "#shortUrlDetails.alias")
    public ShortUrlDetails saveShortUrlDetails(ShortUrlDetails shortUrlDetails){
        return this.shortUrlRepository.insert(shortUrlDetails);
    }

    @Cacheable(value = "shortUrlDetails", key = "#alias", unless="#result == null")
    public ShortUrlDetails getShortUrlDetails(String alias){
        Optional<ShortUrlDetails> optional = this.shortUrlRepository.findById(alias);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }
}
