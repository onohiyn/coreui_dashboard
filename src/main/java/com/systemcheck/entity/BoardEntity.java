package com.systemcheck.entity;


import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;


@Document(collection = "board")
public class BoardEntity implements Serializable {

    @Id
    private String _id;
    private String userId;
    private String title;
    private String text;
    private String deleteYN;
    private String fileUUID;

    @Builder
    public void BoardEntity (String id, String title, String userId, String text, String deleteYN, String fileUUID ){
        this._id = id;
        this.title = title;
        this.userId = userId;
        this.text = text;
        this.deleteYN = deleteYN;
        this.fileUUID = fileUUID;
    }

    public BoardEntity()
    {

    }

    public String get_id() {
        return _id;
    }

    public String getFileUUID() {
        return fileUUID;
    }

    public void setFileUUID(String fileUUID) {
        this.fileUUID = fileUUID;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getDeleteYN() {
        return deleteYN;
    }

    public void setDeleteYN(String deleteYN) {
        this.deleteYN = deleteYN;
    }
}
