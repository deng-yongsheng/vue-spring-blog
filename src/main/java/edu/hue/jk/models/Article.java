package edu.hue.jk.models;

import lombok.Data;

import java.sql.Date;

@Data
public class Article {
    String id;
    String title;
    Date time;
    Integer userid;

    User author;
}
