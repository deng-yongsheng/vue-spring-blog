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
        System.out.println(objectMapper.writeValueAsString(commentMapper.getCommnentListByArticleId("61d2a0a97921802b08fe28b7")));
    }
    /**
     * 根据文章编号和用户编号查询某文章下对应用户的所有评论信息
     * 功能测试
     */
    @Test
    public void getCommentByAandU() throws JsonProcessingException{
        System.out.println(objectMapper.writeValueAsString(commentMapper.getCommentListByAIdandUId("61d2a0a97921802b08fe28b7", 2)));
    }
    /**
     * 根据评论编号、文章编号以及用户编号查询某文章下对应用户的唯一评论
     * 功能测试
     */
    @Test
    public void getuniquecomment() {
        System.out.println(commentMapper.getUniqueComment("61d2a0a97921802b08fe28b7", 2, 18));
    }
    /**
     * 插入一条用户评论记录
     * 功能测试
     */
    @Test
    public void insertcomment(){
        System.out.println(commentMapper.add("0:0:0:0:0:0:0:1", 2, "61d2a0a97921802b08fe28b7", "好啊好啊"));
    }
    /**
     * 根据评论编号和文章编号删除一条评论
     * 功能测试
     */
    @Test
    public void delcomment(){
        System.out.println(commentMapper.del("61d2a0a97921802b08fe28b7", 19));
    }
}
