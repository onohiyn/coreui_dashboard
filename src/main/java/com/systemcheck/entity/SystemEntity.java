package com.systemcheck.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

@Document(collection = "system")
@Getter
@Setter
public class SystemEntity {

    @Id
    private String _id;
    private String system;
    private String domain;
    private String url;
    private String state;
    private String method;
    private double meanTime;

    public SystemEntity(String _id, String system, String domain, String url, String state, String method, double meanTime) {
        this._id = _id;
        this.system = system;
        this.domain = domain;
        this.url = url;
        this.state = state;
        this.method = method;
        this.meanTime = meanTime;
    }

    public SystemEntity() {
    }


}
