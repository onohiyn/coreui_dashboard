package com.systemcheck.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ChatDto {
    private String userId;

    private String userName;
    private String message;
    private String date;

    public ChatDto(String userId, String message, String date, String userName) {
        this.userId = userId;
        this.message = message;
        this.date = date;
        this.userName = userName;
    }

    public ChatDto() {
    }
}
