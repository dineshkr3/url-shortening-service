package com.baml.urlshorteningservice.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.concurrent.atomic.AtomicLong;

/**
 * This class manages the counter allocation. For each individual request of short url creation would call this class to get next counter to be used for short url.
 * This class read counter start and end from application yml which can be configured by the user. Once all the counters are used then it can request for next range of counters.
 *
 * NOTE: In further development, URL shortening server can have multiple instances and should be managed by the Zookeeper.
 * In that cae zookeeper can provide the range of counters to each instance. Also Zookeepr can update the range once it is exhausted.
 */
@Service
public class CounterProviderService {

    private AtomicLong counterCurrent;
    private AtomicLong counterEnd;

    public CounterProviderService(@Value("${counter_range.start}") Long start,
                                  @Value("${counter_range.end}") Long end){
        counterCurrent = new AtomicLong(start);
        counterEnd = new AtomicLong(end);
    }


    /**
     * Return current counter and increment. If current counter is equal to endCounter then new range of counter should be requested.
     * @return long
     */
    public long getNextCounter(){

        if(counterCurrent.compareAndSet(counterEnd.get(), getNewStart())){
            counterEnd.set(getNewEnd());
        }
        ;
        return counterCurrent.getAndIncrement();
    }

    private long getNewStart(){
        //TODO: This is where all the values in the given range is exhausted and new range should be retrieved
        // This method should return starting number of new range

        return 10001;
    }
    private long getNewEnd(){
        //TODO: This is where all the values in the given range is exhausted and new range should be retrieved
        // This method should return new ending number of new range

        return 15000;
    }
}
