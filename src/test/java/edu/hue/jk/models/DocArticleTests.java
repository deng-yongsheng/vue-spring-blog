package edu.hue.jk.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;
import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DocArticleTests {

    @Autowired
    private ObjectMapper objectMapper;
    @Autowired
    private MongoOperations mongoOperations;
    /**
     * 对article对象序列化输出
     * @throws JsonProcessingException
     */
    @Test
    public void jsonfyArticle() throws JsonProcessingException {
        DocArticle article = new DocArticle();
        article.setTitle("标题");
        article.setTime(new Date(System.currentTimeMillis()));
        System.out.println(article);
        //User类转JSON
        //输出结果：{"name":"小民","age":20,"birthday":844099200000,"email":"xiaomin@sina.com"}
        String json = objectMapper.writeValueAsString(article);
        System.out.println(json);
    }

    /**
     * mongodb查询排除字段
     * @throws JsonProcessingException
     */
    @Test
    public void findWithOutContent() throws JsonProcessingException {
        DocArticle articleExample = new DocArticle();
        articleExample.setUserid(1);
        Query query = new Query();
        // 排除字段
        query.fields().exclude("content");
        List<DocArticle> articleList = mongoOperations.find(query,DocArticle.class,"article");
        System.out.println(objectMapper.writeValueAsString(articleList));
    }
}
