package edu.hue.jk.mappers;

import edu.hue.jk.models.Comment;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface CommentMapper {
    //查询模块
    /**
     * 根据文章编号查询评论相关信息
     * @param articleid 文章编号
     * @return
     */
    //根据文章编号查询评论相关信息
    @Select("select * from comment where articleid=#{articleid}")
    List<Comment> getCommnentListByArticleId(@Param("articleid") String articleid);

    //根据文章编号和用户编号查询某文章下对应用户的所有评论信息
    @Select("select * from comment where articleid=#{articleid} and userid = #{userid}")
    List<Comment> getCommentListByAIdandUId(String articleid, Integer userid);

    //根据评论编号、文章编号以及用户编号查询某文章下对应用户的唯一评论
    @Select("select * from comment where articleid=#{articleid} and userid=#{userid} and id=#{id}")
    Comment getUniqueComment(String articleid, Integer userid, Integer id);

    /*
    添加评论模块
     */
    //插入一条用户评论记录
    @Insert("insert into comment(userid, articleid, content,ip) values (#{userid}, #{articleid}, #{content},#{ip})")
    int add(@Param("ip") String ip, @Param("userid") Integer userid, @Param("articleid") String articleid, @Param("content") String content);

    /*
    删除评论模块
     */
    //根据评论编号和文章编号删除一条评论
    @Delete("delete from comment where articleid = #{articleid} and id = #{id}")
    int del(String articleid, Integer id);

}
