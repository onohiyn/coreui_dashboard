package com.systemcheck.entity;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "file")
@Getter
@Setter
public class FileEntity {
    private String uuid;
    private String fileName;
    private String contentType;
    private String fileSize;

    public void setFileSize(String fileSize) {
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

    public String getFileSize() {
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

    public FileEntity() {
    }

}
