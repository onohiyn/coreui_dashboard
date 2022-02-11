package com.systemcheck.entity;


import lombok.Builder;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;
import java.util.Date;


@Document(collection = "board")
public class BoardEntity implements Serializable {

    @Id
    private String _id;
    private String userId;
    private String title;
    private String text;
    private String deleteYN;
    private String fileUUID;
    private String date;
    private String time;

    @Builder
    public BoardEntity(String _id, String userId, String title, String text, String deleteYN, String fileUUID, String date, String time) {
        this._id = _id;
        this.userId = userId;
        this.title = title;
        this.text = text;
        this.deleteYN = deleteYN;
        this.fileUUID = fileUUID;
        this.date = date;
        this.time = time;
    }

    public BoardEntity() {}

    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
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

    public String getTime() {
        return time;
    }
    public void setTime(String time) {
        this.time = time;
    }
}
