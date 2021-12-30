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

    //查询模块
    /*
    功能：根据文章编号查询相关的评论信息（测试成功）
     */
    @GetMapping("/searchAllComByArticleId")
    @ResponseBody
    public String searchAllComByArticleId(Model model, String articleid) {
        List<Comment> commentList = commentMapper.getCommnentListByArticleId(articleid);
        return commentList.toString();
    }

    /*
    功能：根据文章编号和用户编号查询某文章下对应用户的所有评论信息（测试成功）
     */
    @GetMapping("/searchAllCommmentByAIdandUId")
    @ResponseBody
    public String searchAllCommmentByAIdandUId(Model model, String articleid, Integer userid){
        List<Comment> commentList = commentMapper.getCommentListByAIdandUId(articleid, userid);
        return commentList.toString();
    }

    /*
    功能：根据评论编号、文章编号以及用户编号查询某文章下对应用户的评论（测试成功）
     */
    @GetMapping("/searchUniqueComment")
    @ResponseBody
    public String searchUniqueComment(Model model,String articleid, Integer userid, Integer id){
        Comment commentList = commentMapper.getUniqueComment(articleid, userid, id);
        return commentList.toString();
    }

    //添加评论模块
    /*
    功能：插入一条用户评论记录（测试成功）
     */
    @GetMapping("/addcomment")
    @ResponseBody
    public String addcomment(Integer userid, String articleid, String content) {
        int isadd = commentMapper.add(userid, articleid, content);
        if (isadd > 0) {
            return "评论成功!";
        } else {
            return "评论失败!";
        }
    }
    //删除评论模块
    /*
    功能：根据评论编号和文章编号删除一条评论（测试成功）
     */
    @GetMapping("/delCommentByIdandAId")
    @ResponseBody
    public String delCommentByIdandAId(String articleid, Integer id) {
        int isdel = commentMapper.del(articleid, id);
        if (isdel > 0) {
            return "删除评论成功!";
        } else {
            return "删除评论失败!";
        }
    }

}
