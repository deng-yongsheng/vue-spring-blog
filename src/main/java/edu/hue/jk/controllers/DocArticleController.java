package edu.hue.jk.controllers;

import com.fasterxml.jackson.databind.ObjectMapper;
import edu.hue.jk.mappers.DocArticleMapper;
import edu.hue.jk.mappers.UserMapper;
import edu.hue.jk.models.DocArticle;
import edu.hue.jk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;



@Controller
@RequestMapping("/article")
public class DocArticleController {

    @Autowired
    private MongoOperations mongoOperations;
    @Autowired
    private UserMapper userMapper;

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
        model.addAttribute("article", article);
        return "article.html";
    }

    /**
     * 往数据库上传一片文章
     * @param model
     * @param docArticle
     * @param username
     * @return
     */
    @PostMapping("/upload")
    @ResponseBody
    public String upload(Model model, DocArticle docArticle, @RequestHeader(value = "token", required = true) String username) {
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


}
