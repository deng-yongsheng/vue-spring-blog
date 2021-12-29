package edu.hue.jk.mappers;

import edu.hue.jk.models.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AriticleMapper {

    /*
    查询模块
     */
    //查询系统内所有博客文章
    @Select("select * from article")
    List<Article> getAllArticle();
    //根据用户id号获取某个用户的所有博客文章
    @Select("select * from article where userid =#{userid}")
    List<Article> getArticlesByid(Integer userid);
    //根据标题查询系统内的所有博客文章
    @Select("select * from article where title = #{title}")
    List<Article> getArticlesByt(String title);
    //根据标题和用户id查询博客文章
    @Select("select * from article where title = #{title} and userid = #{userid}")
    List<Article> getArticleByidandt(String title, Integer userid);

    /*
    上传模块
     */

    /*
    删除模块
     */

    /*
    编辑模块
     */
}
