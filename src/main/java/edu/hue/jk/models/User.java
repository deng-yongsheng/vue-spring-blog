package edu.hue.jk.models;

import lombok.Data;

import java.util.List;

@Data
public class User {
    Integer id;
    String username;
    String password;
    String usertype;

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", usertype='" + usertype + '\'' +
                ", articles=" + articles +
                '}';
    }

    List<Article> articles;
}
