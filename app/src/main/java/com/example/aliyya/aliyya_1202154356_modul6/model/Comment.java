package com.example.aliyya.aliyya_1202154356_modul6.model;

/**
 * Created by Aliyya on 4/1/2018.
 */

public class Comment {
    String id;
    String username;
    String comment;

    public Comment() {
    }

    public Comment(String id, String username, String comment) {
        this.id=id;
        this.username = username;
        this.comment = comment;
    }

    public String getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getComment() {
        return comment;
    }
}

