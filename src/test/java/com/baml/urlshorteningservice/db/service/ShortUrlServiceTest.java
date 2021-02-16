package com.baml.urlshorteningservice.db.service;

import com.baml.urlshorteningservice.db.model.ShortUrlDetails;
import com.baml.urlshorteningservice.db.repositories.ShortUrlRepository;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.sql.Date;
import java.util.Optional;
@ExtendWith(SpringExtension.class)
public class ShortUrlServiceTest {

    private static final String TEST_ALIAS = "kjhfkjahsf";
    private static final String LONG_URL = "www.google.ie";
    @MockBean
    private ShortUrlRepository shortUrlRepository;
    private ShortUrlService objectToBeTested;
    private ShortUrlDetails shortUrlDetails;
    @BeforeEach
    public void setUp() throws Exception {
        shortUrlDetails = new ShortUrlDetails(TEST_ALIAS, LONG_URL, new Date(System.currentTimeMillis()));
        objectToBeTested = new ShortUrlService(shortUrlRepository);
        objectToBeTested.saveShortUrlDetails(shortUrlDetails);
        Mockito.when(shortUrlRepository.insert(shortUrlDetails)).thenReturn(shortUrlDetails);
        Mockito.when(shortUrlRepository.findById(TEST_ALIAS)).thenReturn(Optional.of(shortUrlDetails));
    }



    @Test
    public void getShortUrlDetails() {

        Assert.assertEquals(LONG_URL, objectToBeTested.getShortUrlDetails(TEST_ALIAS).getLongUrl());
        Assert.assertEquals(LONG_URL, objectToBeTested.getShortUrlDetails(TEST_ALIAS).getLongUrl());
    }
}