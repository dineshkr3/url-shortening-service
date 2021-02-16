package com.baml.urlshorteningservice.controllers;

import com.baml.urlshorteningservice.db.model.ShortUrlDetails;
import com.baml.urlshorteningservice.db.service.ShortUrlService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import java.net.MalformedURLException;
import java.net.URISyntaxException;

/**
 * This class manages the operations related to short urls.
 */
@Slf4j
@RestController
public class UrlRedirectionController {

    private ShortUrlService shortUrlService;
    @Autowired
    public UrlRedirectionController(ShortUrlService shortUrlService){
       this.shortUrlService = shortUrlService;
    }

    /**
     * This method redirects fetches the actual URL from short url and redirects.
     * @param shortUrl
     * @return
     * @throws MalformedURLException
     */
    @GetMapping("/{short-url}")
    public RedirectView redirect(@PathVariable(name = "short-url") String shortUrl) throws URISyntaxException {
        ShortUrlDetails shortUrlDetails = this.shortUrlService.getShortUrlDetails(shortUrl);
        if (shortUrlDetails != null){
           String redirectUrl  = shortUrlDetails.getLongUrl();
           log.info("Redirect URL retrieved for Alias: {} is {}", shortUrl, redirectUrl);
            return new RedirectView(redirectUrl);
        }
        return new RedirectView(HttpStatus.NOT_FOUND.toString());

    }


}
