package edu.hue.jk.mappers;

import edu.hue.jk.models.Article;
import org.apache.ibatis.annotations.Select;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleMapperTests {
    @Autowired
    private AriticleMapper ariticleMapper;

    @Test
    public void test(){
        //测试功能：获取当前系统内的所有博客文章
        System.out.println(ariticleMapper.getAllArticle());
        //测试功能：根据用户id号获取某个用户的所有博客文章
        System.out.println(ariticleMapper.getArticlesByid(1));
        //测试功能：根据标题查询系统内的所有博客文章
        System.out.println(ariticleMapper.getArticlesByt("假标题"));
        //根据标题和用户id查询博客文章
        System.out.println(ariticleMapper.getArticleByidandt("假标题", 2));
    }
}
