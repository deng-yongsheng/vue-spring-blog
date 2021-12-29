package edu.hue.jk.mappers;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleMapperTests {
    @Autowired
    private AriticleMapper ariticleMapper;

    @Test
    public void getArticles() {
        System.out.println(ariticleMapper.getArticleListOfUser(1));
    }
}
