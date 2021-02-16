package com.baml.urlshorteningservice.config;

import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Configuration;

/**
 * Cache Used for providing fast access of Short Urls
 */
@Configuration
@EnableCaching
public class CacheConfig {

}
