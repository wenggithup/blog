package club.banyuan.controller;

import club.banyuan.bean.Comment;
import club.banyuan.bean.User;
import club.banyuan.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class CommentController {
    @Autowired
    private CommentService commentService;
    /*  目的：实现createComment（）方法
     *  功能：添加评论，添加成功后返回blogs/blogid界面
     */

    //用户重新登陆后，触发get请求，应该展示用户刚刚编辑并提交的内容
    @GetMapping("/blogs/{blogId}/comments")
    public String addCommnetByGet(@PathVariable Integer blogId, Comment comment,
                                  HttpSession session) {
        //获取CURRENT_USER字段的值
        User cruuent = (User) session.getAttribute("CURRENT_USER");
        //获取COMMENT_CONTENT的值
        String commentContent = (String) session.getAttribute("COMMENT_CONTENT");
        comment.setBlogId(blogId);
        comment.setUserId(cruuent.getId());
        comment.setContent(commentContent);
        commentService.insertCommnet(comment);
        //重定向至blogs/blogid页面
        return "redirect:/blogs/" + blogId;

    }

    @PostMapping("/blogs/{blogId}/comments")
    public String createCommnet(@PathVariable Integer blogId, Comment comment,
                                HttpSession session, HttpServletRequest request) {
        //接收session中CURRENT_USER字段的值
        User cruuent = (User) session.getAttribute("CURRENT_USER");

        //判断session中的内容是否为空,如果不为空返回blogs界面，否则返回登陆界面
        comment.setBlogId(blogId);
        comment.setUserId(cruuent.getId());
        //添加评论
        commentService.insertCommnet(comment);
        //重定向链接
        return "redirect:/blogs/" + blogId;
    }/* else {
            //如果没有登陆，获取当前的连接并存入session，存储用户内容后跳到login界面
            String commentNext = request.getRequestURI();
            //将当前输入内容存储
            session.setAttribute("COMMENT_CONTENT", comment.getContent());
            //如果获取的内容为空则返回无参登陆页面，否则返回有参登陆
            if (commentNext != null) {
                return "redirect:/login?next=" + commentNext;
            } else {
                return "redirect:/login";
            }


        }*/


}
