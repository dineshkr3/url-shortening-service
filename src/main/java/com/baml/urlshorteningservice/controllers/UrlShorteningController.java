package com.baml.urlshorteningservice.controllers;

import com.baml.urlshorteningservice.db.model.ShortUrlDetails;
import com.baml.urlshorteningservice.db.service.ShortUrlService;
import com.baml.urlshorteningservice.model.UrlInfo;
import com.baml.urlshorteningservice.service.CounterProviderService;
import com.baml.urlshorteningservice.service.ShortUrlAliasBuilderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.MalformedURLException;
import java.net.UnknownHostException;
import java.sql.Date;

/**
 * This Class contains the REST APIs related to Short URL creation.
 */
@Slf4j
@RestController("/url-shortner")
public class UrlShorteningController {


    private Environment environment;
    private ShortUrlService shortUrlService;
    private CounterProviderService counterProviderService;
    private ShortUrlAliasBuilderService shortUrlAliasBuilderService;

    @Autowired
    public UrlShorteningController(Environment environment, ShortUrlService shortUrlService, CounterProviderService counterProviderService,
                                   ShortUrlAliasBuilderService shortUrlAliasBuilderService){
        this.environment = environment;
        this.shortUrlService = shortUrlService;
        this.counterProviderService = counterProviderService;
        this.shortUrlAliasBuilderService = shortUrlAliasBuilderService;
    }

    /**
     * This method generates the short url, saves in DB and returns the generated short url.
     * @param urlInfo
     * @return
     * @throws MalformedURLException
     */
    @PostMapping("/generate")
    public ResponseEntity<String> generateShortUrl(@RequestBody UrlInfo urlInfo) throws MalformedURLException, UnknownHostException {
        long counter = this.counterProviderService.getNextCounter();
        String alias = this.shortUrlAliasBuilderService.buildShortUrlAlias(counter);
        log.info("Alias Generated for {} is :{}", urlInfo.getUrl(), alias);
        String url = buildShortUrl(alias);
        saveShortUrlToDatabase(urlInfo, alias);
        return new ResponseEntity<>(url, HttpStatus.CREATED);
    }

    private void saveShortUrlToDatabase(UrlInfo urlInfo, String alias){
        ShortUrlDetails shortUrlDetails = new ShortUrlDetails(alias, urlInfo.getUrl(), new Date(System.currentTimeMillis()));
        this.shortUrlService.saveShortUrlDetails(shortUrlDetails);
    }

    private String buildShortUrl(String alias) throws MalformedURLException, UnknownHostException {
        String port  = environment.getProperty("server.port");
        //TODO: For now Localhost address is taken which can be replace with the domain address in production
        //String serverAddress = InetAddress.getLocalHost().getHostAddress();
        String serverAddress = "localhost";
        UriComponents uriComponents = UriComponentsBuilder.newInstance()
                .scheme("http")
                .host(serverAddress)
                .port(port)
                .path(alias)
                .build();
        return uriComponents.toString();

    }


}
