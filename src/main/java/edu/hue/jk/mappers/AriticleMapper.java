package edu.hue.jk.mappers;

import edu.hue.jk.models.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface AriticleMapper {

    // todo
    // 查询文章，上传文章，删除文章，获取某个用户的所有文章
    @Select("select * from article where userid =#{userid}")
    List<Article> getArticleListOfUser(Integer userid);
}
