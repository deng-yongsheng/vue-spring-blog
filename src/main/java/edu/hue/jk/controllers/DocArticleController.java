package edu.hue.jk.controllers;

import edu.hue.jk.mappers.DocArticleMapper;
import edu.hue.jk.mappers.UserMapper;
import edu.hue.jk.models.DocArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


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

    @PostMapping("/upload")
    @ResponseBody
    public String upload(Model model, DocArticle article, @RequestHeader(value = "token", required = true) String username) {
        if (userMapper.getUserByName(username)!=null){
            mongoOperations.save(article, "article");
            return "上传成功";
        }else{
            return "没有上传权限，请检查是否已经登录";
        }

    }


}
