package edu.hue.jk.mappers;

import edu.hue.jk.models.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    //根据文章编号查询评论相关信息
    @Select("select * from comment where articleid=#{articleid}")
    List<Comment> getCommnentListByArticleId(String articleid);
    //根据文章编号和用户编号查询某文章下对应用户的所有评论信息
    @Select("select * from comment where articleid=#{articleid} and userid = #{userid}")
    List<Comment> getCommentByAIdandUId(String articleid, Integer userid);
    //根据评论编号、文章编号以及用户编号查询某文章下对应用户的唯一一条评论
    @Select("select * from comment where articleid=#{articleid} and userid=#{userid} and id=#{id}")
    Comment getUniqueComment(String articleid, Integer userid, Integer id);
    //用户插入一条评论
    @Insert("insert into comment(userid, articleid, content) values (#{userid}, #{articleid}, #{content})")
    int add(Integer userid, String articleid, String content);
    //用户删除一条评论
    @Delete("delete from comment where id = #{id}")
    int del(Integer id);
    //用户修改评论
    @Update("update comment set userid = #{userid}, content = #{content} where id = #{id}")
    int update(Integer userid, String content);

}
