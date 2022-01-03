package edu.hue.jk.models;

import lombok.Data;
import net.minidev.json.annotate.JsonIgnore;

import java.util.List;

@Data
public class User {
    Integer id;
    String username;
    @JsonIgnore
    String password;
    String usertype;
    List<Article> articles;
}
