package com.systemcheck.entity;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;


@Document(collection = "board")
@Getter
@Setter
public class BoardEntity {

    @Id
    private String _id;
    private String userId;
    private String title;
    private String text;
    private String deleteYN;

    @Builder
    public void UserCollection (String id, String title, String userId, String text, String deleteYN ){
        this._id = id;
        this.title = title;
        this.userId = userId;
        this.text = text;
        this.deleteYN = deleteYN;
    }


}
