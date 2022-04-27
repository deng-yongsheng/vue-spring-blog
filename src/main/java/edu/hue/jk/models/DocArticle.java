package edu.hue.jk.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonView;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Date;

@Data
@Document
@JsonView
public class DocArticle {
    @Id
    String id;
    String title;
    String description;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date time;
    Integer userid;
    String username;
    String content;

    User author;
}
