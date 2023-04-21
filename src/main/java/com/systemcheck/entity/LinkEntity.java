package com.systemcheck.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "link")
@Getter
@Setter
public class LinkEntity {

    @Id
    private String _id;
    private String system;
    private String url;
    private String domain;

    public LinkEntity(String _id, String system, String url, String domain) {
        this._id = _id;
        this.system = system;
        this.url = url;
        this.domain = domain;
    }

    public LinkEntity() {

    }
}
