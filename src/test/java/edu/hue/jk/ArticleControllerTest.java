package edu.hue.jk;

import edu.hue.jk.models.DocArticle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ArticleControllerTest {
    @Autowired
    private MongoOperations mongoOperations;

    @Test
    public void insertTest() {

        DocArticle docArticle = new DocArticle();
        docArticle.setTitle("测试的一篇文章标题");
        docArticle.setContent("测试的一篇文章正文");
        docArticle.setId(UUID.randomUUID().toString());
        mongoOperations.save(docArticle, "article");
    }
}
