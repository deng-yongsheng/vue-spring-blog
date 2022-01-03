package edu.hue.jk.models;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Date;

@SpringBootTest
@RunWith(SpringRunner.class)
public class DocArticleTests {

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
        System.out.println(article);
        ObjectMapper mapper = new ObjectMapper();

        //User类转JSON
        //输出结果：{"name":"小民","age":20,"birthday":844099200000,"email":"xiaomin@sina.com"}
        String json = mapper.writeValueAsString(article);
        System.out.println(json);
    }
}
