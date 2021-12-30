package edu.hue.jk.mappers;

import edu.hue.jk.models.DocArticle;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.UUID;

@SpringBootTest
@RunWith(SpringRunner.class)
/**
 * 读取mongodb服务器里的所有文章内容
 */
public class DocArticleMapperTests {
    @Autowired
    private MongoOperations mongoOperations;

    @Test
    /**
     * 插入一条新闻记录
     */
    public void insertTest() {

        DocArticle docArticle = new DocArticle();
        docArticle.setTitle("132测试的一篇文章标题");
        docArticle.setContent("123测试的一篇文章正文");
        docArticle.setId(UUID.randomUUID().toString());
        docArticle.setUserid(123);
        mongoOperations.save(docArticle, "article");
    }

    @Test
    /**
     * 输出所有的文章信息
     */
    public void findTest() {
        List<DocArticle> docArticleList = mongoOperations.findAll(DocArticle.class, "article");
        System.out.println(docArticleList.size());
        System.out.println(docArticleList);
    }

    @Test
    /**
     * 查询指定id的文章
     */
    public void findArticleByid() {
        DocArticle docArticle = mongoOperations.findById("2e3a3acd-44b5-4793-914c-608a5d75b66c", DocArticle.class, "article");
        System.out.println(docArticle);
    }

    @Test
    /**
     * 查询指定用户id的文章
     */
    public void findArticleByUserid() {
        DocArticle docArticle = mongoOperations.findOne(new Query(Criteria.where("userid").is(Integer.valueOf(123))), DocArticle.class, "article");
        System.out.println(docArticle);
    }
}
