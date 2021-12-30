package edu.hue.jk.controllers;

import edu.hue.jk.mappers.DocArticleMapper;
import edu.hue.jk.models.DocArticle;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
@RequestMapping("/article")
public class DocArticleController {
    //    DocArticleMapper docArticleMapper = new DocArticleMapper();
    @Autowired
    private MongoOperations mongoOperations;

    @GetMapping("/{articleid}")
    @ResponseBody
    public String getArticle(@PathVariable("articleid") String articleid) {

        return mongoOperations.findById(articleid, DocArticle.class, "article").toString();
    }


}
