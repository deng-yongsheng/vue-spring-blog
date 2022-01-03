package edu.hue.jk.controllers;

import edu.hue.jk.mappers.CommentMapper;
import edu.hue.jk.models.Comment;
import edu.hue.jk.models.User;
import edu.hue.jk.utils.IpUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/api")
public class CommentController {
    @Autowired
    private CommentMapper commentMapper;

    /**
     * 根据文章编号查询相关的评论信息（测试成功）
     *
     * @param articleid
     * @return
     */
    @GetMapping("/searchAllComByArticleId")
    @ResponseBody
    public String searchAllComByArticleId(@RequestParam String articleid) {
        List<Comment> commentList = commentMapper.getCommnentListByArticleId(articleid);
        return commentList.toString();
    }

    /**
     * 根据文章编号和用户编号查询某文章下对应用户的所有评论信息（测试成功）
     *
     * @param articleid
     * @param userid
     * @return
     */
    @GetMapping("/searchAllCommmentByAIdandUId")
    @ResponseBody
    public String searchAllCommmentByAIdandUId(@RequestParam String articleid, @RequestParam Integer userid) {
        List<Comment> commentList = commentMapper.getCommentListByAIdandUId(articleid, userid);
        return commentList.toString();
    }

    /**
     * 根据评论编号、文章编号以及用户编号查询某文章下对应用户的评论（测试成功）
     *
     * @param articleid
     * @param userid
     * @param id
     * @return
     */
    @GetMapping("/searchUniqueComment")
    @ResponseBody
    public String searchUniqueComment(@RequestParam String articleid, @RequestParam Integer userid, Integer id) {
        Comment commentList = commentMapper.getUniqueComment(articleid, userid, id);
        return commentList.toString();
    }

    /**
     * 添加一条评论
     *
     * @param request   用户请求,用来提取IP地址
     * @param articleid 用户评论的文章id
     * @param content   评论内容
     * @param userid    用户id
     * @return
     */
    @PostMapping("/addcomment")
    @ResponseBody
    public String addcomment(HttpServletRequest request, @RequestParam String articleid, @RequestParam String content, @RequestParam Integer userid) {
        String ip = IpUtils.getRealIp(request);
        int isadd = commentMapper.add(ip, userid, articleid, content);
        if (isadd > 0) {
            return "评论成功!";
        } else {
            return "评论失败!";
        }
    }

    /**
     * 根据评论编号和文章编号删除一条评论（测试成功）
     *
     * @param articleid
     * @param id
     * @return
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
