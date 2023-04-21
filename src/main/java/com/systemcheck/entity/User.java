package com.systemcheck.entity;


import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "user")
@Getter
@Setter
public class User {

    @Id
    private String _id;
    private String userId;
    private String passwd;
    private String role;
    private String userName;

    @Builder
    public void UserCollection (String id, String userId, String passwd, String role, String userName ){
        this._id = id;
        this.userId = userId;
        this.passwd = passwd;
        this.role = role;
        this.userName = userName;
    }


}
