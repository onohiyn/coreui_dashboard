package com.systemcheck.entity;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;

//@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Document(collection = "userSignTmp")
@Getter
@Setter
public class NewUserSign {

    @Id
    private String _id;
    private String userId;
    private String passwd;
    private String email;
    private String confirm;

    @Builder
    public void NewUserCollection (String id, String userId, String passwd, String email, String confirm ){
        this._id = id;
        this.userId = userId;
        this.passwd = passwd;
        this.email = email;
        this.confirm = confirm;
    }

    public NewUserSign(){

    }


}