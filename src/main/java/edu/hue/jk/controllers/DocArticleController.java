package edu.hue.jk.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hue.jk.mappers.UserMapper;
import edu.hue.jk.models.DocArticle;
import edu.hue.jk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.util.List;


@Controller
@RequestMapping("/article")
public class DocArticleController {

    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ObjectMapper objectMapper;

    /**
     * 根据uuid查询一篇文章
     *
     * @param model
     * @param articleid
     * @return thymeleaf模板名称
     */
    @GetMapping("/{articleid}")
    public String getArticle(Model model, @PathVariable(value = "articleid", required = true) String articleid) {
        DocArticle article = mongoOperations.findById(articleid, DocArticle.class, "article");
        article.setAuthor(userMapper.getUserById(article.getUserid()));
        model.addAttribute("article", article);
        return "article.html";
    }

    /**
     * 往数据库上传一篇文章
     *
     * @param docArticle
     * @param username   通过headers传入的token字段
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(DocArticle docArticle, @RequestHeader(value = "token", required = true) String username) {
        User user = userMapper.getUserByName(username);
        if (user != null) {
            if (docArticle.getTime() == null) {
                docArticle.setTime(new Date(System.currentTimeMillis()));
            }
            docArticle.setUsername(username);
            docArticle.setUserid(user.getId());
            mongoOperations.save(docArticle, "article");
            return "上传成功";
        } else {
            return "没有上传权限，请检查是否已经登录";
        }
    }

    /**
     * 查询当前数据库已经有的文章，不包含文章正文
     *
     * @return 不包含文章正文
     * @throws JsonProcessingException
     */
    @RequestMapping("/getAll")
    @ResponseBody
    public String getAll() throws JsonProcessingException {
        // 排除content字段
        Query query = new Query();
        query.fields().exclude("content");
        List<DocArticle> articleList = mongoOperations.find(query, DocArticle.class, "article");
        // 加载作者信息
        for (DocArticle article : articleList) {
            article.setAuthor(userMapper.getUserById(article.getUserid()));
        }
        return objectMapper.writeValueAsString(articleList);
    }

    /**
     * 删除一篇文章
     *
     * @param articleid 要删除的文章编号
     * @return 返回删除了的文章
     * @throws JsonProcessingException
     */
    @RequestMapping("/del/{articleid}")
    @ResponseBody
    public String del(@PathVariable(value = "articleid") String articleid) throws JsonProcessingException {
        System.out.println("articleid: " + articleid);
        Query query = new Query(Criteria.where("id").is(articleid));
        DocArticle docArticle = mongoOperations.findAndRemove(query, DocArticle.class, "article");
        return objectMapper.writeValueAsString(docArticle);
    }
}
