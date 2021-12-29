package edu.hue.jk.controllers;

import edu.hue.jk.mappers.CommentMapper;
import edu.hue.jk.models.Comment;
import edu.hue.jk.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;

    /*
    功能：根据文章编号查询相关的评论信息
     */
    @GetMapping("/searchAllCom")
    @ResponseBody
    public String searchAllcom(Model model, String articleid) {
        List<Comment> commentList = commentMapper.getCommnentListByArticleId(articleid);
        return commentList.toString();
    }

    /*
    功能：用户插入一条评论
     */
    @GetMapping("/addcomment")
    @ResponseBody
    public String addcomment(Integer userid, String articleid, String content){
        if(userid == null){
            int isadd = commentMapper.add(userid, articleid, content);
            if(isadd > 0){
                return "当前用户属于匿名评论";
            }else{
                return "评论失败!";
            }
        }
        else{
            int isadd = commentMapper.add(userid, articleid, content);
            if(isadd > 0){
                return "当前用户名为"+userid;
                /*
                todo
                未知返回
                 */
            }else{
                return "评论失败!";
            }

        }

    }


}
