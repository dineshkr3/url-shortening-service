package com.baml.urlshorteningservice.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.junit.Assert.assertNotNull;
@ExtendWith(SpringExtension.class)
public class ShortUrlAliasBuilderServiceTest {

    private ShortUrlAliasBuilderService objectToBeTested;
    @BeforeEach
    public void setUp() throws Exception {
        objectToBeTested = new ShortUrlAliasBuilderService();
    }

    @Test
    public void buildShortUrlAlias() {
        assertNotNull(objectToBeTested.buildShortUrlAlias(1L));
    }
}