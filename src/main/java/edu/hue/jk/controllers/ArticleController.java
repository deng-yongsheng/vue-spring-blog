package edu.hue.jk.controllers;

import edu.hue.jk.mappers.AriticleMapper;
import edu.hue.jk.models.Article;
import edu.hue.jk.models.Comment;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class ArticleController {
    @Autowired
    private AriticleMapper ariticleMapper;

    /**
     * 查询系统内所有文章
     * @param model
     * @return
     */
    @GetMapping("/selectAllArticles")
    @ResponseBody
    public String selectAllArticles(Model model) {
        List<Article> articlesList = ariticleMapper.getAllArticle();
        ;
        return articlesList.toString();
    }

    /**
     * 根据用户id号获取某个用户的所有文章
     * @param model
     * @param userid
     * @return
     */
    @GetMapping("/selectArticlesByUserid")
    @ResponseBody
    public String selectArticlesByUserid(Model model, Integer userid) {
        List<Article> articleList = ariticleMapper.getArticlesByid(userid);
        return articleList.toString();
    }

    /**
     * 根据标题查询系统内的所有同名文章
     * @param model
     * @param title
     * @return
     */
    @GetMapping("/selectArticlesByTitle")
    @ResponseBody
    public String selectArticlesByTitle(Model model, String title) {
        List<Article> articleList = ariticleMapper.getArticlesByt(title);
        return articleList.toString();
    }

    /**
     * 根据标题和用户id查询文章
     * @param model
     * @param title
     * @param userid
     * @return
     */
    @GetMapping("/selectArticlesByTitleandUserid")
    @ResponseBody
    public String selectArticlesByTitleandUserid(Model model, String title, Integer userid) {
        List<Article> articleList = ariticleMapper.getArticleByidandt(title, userid);
        return articleList.toString();
    }

    /**
     * 根据文章id查询对应文章
     * @param id
     * @return
     */
    @GetMapping("/selectArticlesById")
    @ResponseBody
    public String selectArticlesById(String id) {
        Article uniquearticle = ariticleMapper.getUniqueArticle(id);
        return uniquearticle.toString();
    }


    /**
     * 根据用户id号删除指定用户的所有文章
     * @param userid 要删除的用户id
     * @return 删除成功返回 > 0
     */
    @GetMapping("/delArticlesByUserid")
    @ResponseBody
    public String delArticlesByUserid(Integer userid) {
        int isdelByUserid = ariticleMapper.delByUserid(userid);
        if (isdelByUserid > 0) {
            return "删除文章成功!";
        } else {
            return "删除文章失败！";
        }
    }

    /**
     * 根据文章id号删除文章
     * @param id
     * @return
     */
    @GetMapping("/delArticlesById")
    @ResponseBody
    public String delArticlesById(String id) {
        int isdelById = ariticleMapper.delByid(id);
        if (isdelById > 0) {
            return "删除文章成功!";
        } else {
            return "删除文章失败！";
        }
    }
}
