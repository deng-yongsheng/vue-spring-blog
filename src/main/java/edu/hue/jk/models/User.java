package edu.hue.jk.models;

import lombok.Data;

import java.util.List;

@Data
public class User {
    Integer id;
    String username;
    String password;
    String usertype;

    List<Article> articles;
}
