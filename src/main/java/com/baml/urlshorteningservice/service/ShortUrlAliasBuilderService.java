package com.baml.urlshorteningservice.service;

import io.seruco.encoding.base62.Base62;
import org.springframework.stereotype.Service;

/**
 * This is utility class to generate alias for the counter provided.
 */

@Service
public class ShortUrlAliasBuilderService {

    public String buildShortUrlAlias(long counter){
        Base62 base62 = Base62.createInstance();
        final byte[] encoded = base62.encode(String.valueOf(counter).getBytes());
        return new String(encoded);
    }

}
