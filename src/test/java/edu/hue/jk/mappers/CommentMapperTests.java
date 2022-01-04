package edu.hue.jk.mappers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class CommentMapperTests {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 根据文章编号查询评论相关信息
     * 功能测试
     */
    @Test
    public void getCommentList() throws JsonProcessingException {
        System.out.println(objectMapper.writeValueAsString(commentMapper.getCommnentListByArticleId("61d29e957921802b08fe28b6")));
    }
}
