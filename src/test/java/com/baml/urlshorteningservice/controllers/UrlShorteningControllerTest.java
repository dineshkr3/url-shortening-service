package com.baml.urlshorteningservice.controllers;

import com.baml.urlshorteningservice.db.service.ShortUrlService;
import com.baml.urlshorteningservice.model.UrlInfo;
import com.baml.urlshorteningservice.service.CounterProviderService;
import com.baml.urlshorteningservice.service.ShortUrlAliasBuilderService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.env.Environment;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.MalformedURLException;
import java.net.UnknownHostException;

import static org.junit.Assert.assertNotNull;
@ExtendWith(SpringExtension.class)
public class UrlShorteningControllerTest {

    @MockBean
    private Environment environment;
    @MockBean
    private ShortUrlService shortUrlService;
    @MockBean
    private CounterProviderService counterProviderService;

    private ShortUrlAliasBuilderService shortUrlAliasBuilderService;


    private UrlShorteningController objectToBeTested;
    @BeforeEach
    public void setUp() throws Exception {
        shortUrlAliasBuilderService = new ShortUrlAliasBuilderService();
        objectToBeTested = new UrlShorteningController(environment,shortUrlService,counterProviderService,shortUrlAliasBuilderService);
        Mockito.when(this.counterProviderService.getNextCounter()).thenReturn(12345L);
        Mockito.when(this.environment.getProperty("server.port")).thenReturn("8080");
        Mockito.when(this.environment.getProperty("server.address")).thenReturn("localhost");
    }

    @Test
    public void generateShortUrl() throws MalformedURLException, UnknownHostException {
        UrlInfo urlInfo = new UrlInfo();
        urlInfo.setUrl("www.google.ie");
        assertNotNull(objectToBeTested.generateShortUrl(urlInfo));
    }

    @Test
    public void getLongUrl() {
    }
}