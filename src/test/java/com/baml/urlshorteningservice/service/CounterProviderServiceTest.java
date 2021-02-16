package com.baml.urlshorteningservice.service;

import org.junit.Assert;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class CounterProviderServiceTest {

    private CounterProviderService objectToBeTested;

    @BeforeEach
    public void setUp(){
        objectToBeTested = new CounterProviderService(1L, 3L);
    }

    @Test
    public void getNextCounter() {
        Assert.assertEquals(1L, objectToBeTested.getNextCounter());;
    }

    @Test
    public void getNextCounter_after_the_range_finished() {
        Assert.assertEquals(1L, objectToBeTested.getNextCounter());
        Assert.assertEquals(2L, objectToBeTested.getNextCounter());
        Assert.assertEquals(10001L, objectToBeTested.getNextCounter());
    }
}