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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @Autowired
    private CommentService commentService;

    //创建博客
    @GetMapping("/blogs/create")
    String showCreatePage() {
        return "create";
    }
/*    String showCreatePage(HttpSession session, HttpServletRequest request) {
        //取出CURRENT_USER字段的值
        User user = (User) session.getAttribute("CURRENT_USER");
        //如果用户为空，则表示当前用户没有登陆，应加上当前的url返回到login界面，否则读取create界面
        if (user != null) {
            return "create";
        } else {
            //获取当前的uri
            String currentUri = request.getRequestURI();
            return "redirect:/login?next=" + currentUri;
        }

    }*/


    //处理提交blog请求（添加博客内容），前台传参只有 title和content
    @PostMapping("/blogs")
    //public String createBlog(@RequestParam String title,@RequestParam String content){
    public String createBlog(Blog blog, HttpSession session) {
        User user = (User) session.getAttribute("CURRENT_USER");
        blog.setUserId(user.getId());
        blogService.insertBlogs(blog);
        //重定向到 blogs/blogs.getId中
        return "redirect:/blogs/" + blog.getId();
    }

    //user用户界面
    @GetMapping("/user/{username}")
    String getUserBlogs(@PathVariable String username,
                        @RequestParam(defaultValue = "1", required = false) Integer page,
                        @RequestParam(defaultValue = "10", required = false) Integer size, Model model) {
        //通过username返回User对象
        User user = userService.findUserByName(username);
       /* List <Blog> blogs=blogService.findBlogByUsernameWithPageInfo(username,page,size);

        boolean hasPre=(page==1?false:true);
        List<Blog> nextPageBlogs = blogService.findBlogByUsernameWithPageInfo(username, page+1, size);
        boolean hasNext = (nextPageBlogs.size() == 0) ? false : true;
        model.addAttribute("user",user);
        model.addAttribute("blogs",blogs);
        model.addAttribute("hasPre",hasPre);
        model.addAttribute("hasNext",hasNext);
        model.addAttribute("page",page);*/
        PageInfo blogs = blogService.getUserBlog(username, page, size);
        model.addAttribute("user", user);
        model.addAttribute("blogs", blogs);
        return "list";
    }

    //返回点击标题后的博客内容
    @GetMapping("/blogs/{id}")
    String getBlog(@PathVariable Integer id, Model model) {
        //通过blogid返回Blog对象
        Blog blog = blogService.findBlogById(id);
        //通过blogid返回Comment对象集合
        List<Comment> comment = commentService.findCommentByBlogId(id);
        model.addAttribute("blog", blog);
        model.addAttribute("comments", comment);
        return "item";
    }

}
