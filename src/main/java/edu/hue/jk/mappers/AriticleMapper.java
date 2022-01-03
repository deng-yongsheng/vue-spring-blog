package edu.hue.jk.mappers;

import edu.hue.jk.models.Article;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

@Mapper
public interface AriticleMapper {

    //查询模块

    /**
     * 查询系统内所有文章
     * @return
     */
    @Select("select * from article")
    List<Article> getAllArticle();

    /**
     * 根据用户id号获取某个用户的所有文章
     * @param userid
     * @return
     */
    @Select("select * from article where userid =#{userid}")
    List<Article> getArticlesByid(Integer userid);
    //根据标题查询系统内的所有同名文章
    @Select("select * from article where title = #{title}")
    List<Article> getArticlesByt(String title);
    //根据标题和用户id查询文章
    @Select("select * from article where title = #{title} and userid = #{userid}")
    List<Article> getArticleByidandt(String title, Integer userid);
    //根据文章id查询对应文章
    @Select("select * from article where id = #{id}")
    Article getUniqueArticle(String id);


    /*
    上传文章模块
     */




    /*
    删除文章模块
     */
    //根据用户id号删除指定用户的所有文章
    @Delete("delete from article where userid = #{userid}")
    int delByUserid(Integer userid);
    //根据文章id号删除文章
    @Delete("delete from article where id = #{id}")
    int delByid(String id);


    /*
    编辑文章模块
     */

}
