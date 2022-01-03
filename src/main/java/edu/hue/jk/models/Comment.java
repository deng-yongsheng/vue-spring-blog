package edu.hue.jk.models;

import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    Integer id;
    Integer userid;
    Integer articleid;
    Date time;
    String content;
    String ip;

    @Override
    public String toString() {
        return "Comment{" +
                "\"id\"=\"" + id +
                "\", \"userid\"=\"" + userid +
                "\", \"articleid\"=\"" + articleid +
                "\", \"time\"=\"" + time +
                "\", \"content\"=\"" + content + "\"}";
    }
}
