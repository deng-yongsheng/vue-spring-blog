package edu.hue.jk.models;

import lombok.Data;
import org.springframework.data.mongodb.core.mapping.Document;

import java.sql.Date;

@Data
@Document
public class DocArticle {

    String id;
    String title;
    Date time;
    Integer userid;
    String content;

    @Override
    public String toString() {
        return "{\"DocArticle\":{"
                + "\"id\":\""
                + id + '\"'
                + ",\"title\":\""
                + title + '\"'
                + ",\"time\":\""
                + time + '\"'
                + ",\"userid\":"
                + userid
                + ",\"content\":\""
                + content + '\"'
                + "}}";
    }
}
