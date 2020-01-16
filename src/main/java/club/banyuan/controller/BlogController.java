package club.banyuan.controller;

import club.banyuan.bean.Blog;
import club.banyuan.bean.Comment;
import club.banyuan.bean.User;
import club.banyuan.dao.CommentDao;
import club.banyuan.service.BlogService;
import club.banyuan.service.CommentService;
import club.banyuan.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;
    //创
    @GetMapping("/blogs/create")
    String showCreatePage(){
        return "create";
    }

    //添加评论
    @PostMapping("/blogs/{blogid}/comments")
    public String createCommnet(@PathVariable Integer blogid,@RequestParam String content){
        Blog blog= blogService.findBlogById(blogid);
        Comment comment=new Comment();
        comment.setBlogId(blog.getId());
        comment.setUserId(blog.getUserId());
        comment.setContent(content);
        //添加评论
        commentService.insertCommnet(comment);
        //重定向链接
        return "redirect:/blogs/" + blogid;
    }

    //处理提交blog请求（添加博客内容），前台传参只有 title和content
    @PostMapping("/blogs")
    public String createBlog(@RequestParam String title,@RequestParam String content){
        Blog blog=new Blog();
        blog.setTitle(title);
        blog.setContent(content);
        //设置同一个用户下的博客
        Integer userId=99;
        blog.setUserId(userId);
        blogService.insertBlogs(blog);
        //重定向到 blogs/blogs.getId中
        return "redirect:/blogs/"+blog.getId();
    }

    //user用户界面
    @GetMapping("/user/{username}")
    String getUserBlogs(@PathVariable String username,
                        @RequestParam(defaultValue = "1",required = false) Integer page,
                        @RequestParam(defaultValue = "10",required = false) Integer size, Model model){
        //通过username返回User对象
        User user=userService.findUserByName(username);
       /* List <Blog> blogs=blogService.findBlogByUsernameWithPageInfo(username,page,size);

        boolean hasPre=(page==1?false:true);
        List<Blog> nextPageBlogs = blogService.findBlogByUsernameWithPageInfo(username, page+1, size);
        boolean hasNext = (nextPageBlogs.size() == 0) ? false : true;
        model.addAttribute("user",user);
        model.addAttribute("blogs",blogs);
        model.addAttribute("hasPre",hasPre);
        model.addAttribute("hasNext",hasNext);
        model.addAttribute("page",page);*/
        PageInfo blogs=blogService.getUserBlog(username,page,size);
        model.addAttribute("user",user);
        model.addAttribute("blogs",blogs);
        return "list";
    }

    //返回点击标题后的博客内容
    @GetMapping("/blogs/{id}")
    String getBlog(@PathVariable Integer id,Model model){
        //通过blogid返回Blog对象
        Blog blog= blogService.findBlogById(id);
        //通过blogid返回Comment对象集合
        List<Comment> comment = commentService.findCommentByBlogId(id);
        model.addAttribute("blog",blog);
        model.addAttribute("comments",comment);
        return "item";
    }

}
