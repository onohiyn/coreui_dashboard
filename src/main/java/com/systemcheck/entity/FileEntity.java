package com.systemcheck.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.persistence.Id;
import java.io.Serializable;

@Document(collection = "file")
@Getter
@Setter
public class FileEntity implements Serializable {

    @Id
    private String _id;
    private String uuid;
    private String fileName;
    private String contentType;
    private long fileSize;

    public String get_id() {
        return _id;
    }

    public void set_id(String _id) {
        this._id = _id;
    }

    public void setFileSize(long fileSize) {
        this.fileSize = fileSize;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public void setUuid(String uuid) {
        this.uuid = uuid;
    }

    public long getFileSize() {
        return fileSize;
    }

    public String getContentType() {
        return contentType;
    }

    public String getFileName() {
        return fileName;
    }

    public String getUuid() {
        return uuid;
    }

    public FileEntity(String _id, String uuid, String fileName, String contentType, long fileSize) {
        this._id = _id;
        this.uuid = uuid;
        this.fileName = fileName;
        this.contentType = contentType;
        this.fileSize = fileSize;
    }

    public FileEntity() {
    }

}
