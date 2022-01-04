package edu.hue.jk.models;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;

import java.util.Date;

@Data
public class Comment {
    Integer id;
    Integer userid;
    String articleid;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    Date time;
    String content;
    String ip;
}
