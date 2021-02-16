package com.baml.urlshorteningservice;

import com.baml.urlshorteningservice.model.UrlInfo;
import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.env.ConfigurableEnvironment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class UrlShorteningServiceApplicationTest {
    private static final String TEST_URL = "https://www.facebook.com/";

    @LocalServerPort
    protected int port;

    @Autowired
    private ConfigurableEnvironment environment;
    @BeforeEach
    public void setUp(){

    }

    @Test
    public void testShortUrlGenerationAndRedirection(){
        RestTemplate restTemplate = new RestTemplate();
        final String baseUrl = "http://localhost:" + port ;
        final String shortUrlGenApi = baseUrl + "/generate";
        UrlInfo urlInfo = new UrlInfo();
        urlInfo.setUrl(TEST_URL);
        //Test Generation
        ResponseEntity<String> response = restTemplate.postForEntity(shortUrlGenApi,  urlInfo,String.class);
        Assert.assertNotNull(response);
        //Test Redirection
        ResponseEntity responseEntity = restTemplate.getForEntity(response.getBody().replace(":0", ":"+ String.valueOf(port)), ResponseEntity.class);
        Assert.assertEquals(TEST_URL, responseEntity.getHeaders().getLocation().toString());


    }
}