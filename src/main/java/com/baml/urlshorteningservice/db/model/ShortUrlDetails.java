package com.baml.urlshorteningservice.db.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;


@Getter
@Setter
@Document
public class ShortUrlDetails {
    @Id
    private String alias;
    private String longUrl;
    private Date createdAt;

    @PersistenceConstructor
    public ShortUrlDetails(final String alias, final String longUrl, Date createdAt){
        this.alias = alias;
        this.longUrl = longUrl;
        this.createdAt = createdAt;
    }
}
