package edu.hue.jk.controllers;

import edu.hue.jk.mappers.DocArticleMapper;
import edu.hue.jk.models.DocArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/article")
public class DocArticleController {

    @Autowired
    private MongoOperations mongoOperations;

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
}
