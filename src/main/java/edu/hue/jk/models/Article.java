package edu.hue.jk.models;

import lombok.Data;

import java.sql.Date;

@Data
public class Article {
    Integer id;
    String uuid;
    String title;
    Date datetime;

    User Author;
}
