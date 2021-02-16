package com.baml.urlshorteningservice.controllers;

import com.baml.urlshorteningservice.db.model.ShortUrlDetails;
import com.baml.urlshorteningservice.db.service.ShortUrlService;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URISyntaxException;
import java.sql.Date;
@ExtendWith(SpringExtension.class)
public class UrlRedirectionControllerTest {

    private static final String TEST_ALIAS = "kjhfkjahsf";
    private static final String LONG_URL = "www.google.ie";


    @MockBean
    private ShortUrlService shortUrlService;

    private UrlRedirectionController objectToBeTested;

    @BeforeEach
    public void setUp() throws Exception {
        objectToBeTested = new UrlRedirectionController(shortUrlService);
        ShortUrlDetails shortUrlDetails = new ShortUrlDetails(TEST_ALIAS, LONG_URL, new Date(System.currentTimeMillis()));
        Mockito.when(shortUrlService.getShortUrlDetails(TEST_ALIAS)).thenReturn(shortUrlDetails);
    }

    @Test
    public void redirect() throws URISyntaxException {
        Assert.assertEquals(LONG_URL, objectToBeTested.redirect(TEST_ALIAS).getUrl().toString());
    }
}