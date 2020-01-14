package club.banyuan.controller;

import club.banyuan.bean.Blog;
import club.banyuan.bean.User;
import club.banyuan.service.BlogService;
import club.banyuan.service.UserService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class BlogController {
    @Autowired
    private BlogService blogService;
    @Autowired
    private UserService userService;
    @GetMapping("/user/{username}")
    String getUserBlogs(@PathVariable String username,
                        @RequestParam(defaultValue = "1",required = false) Integer page,
                        @RequestParam(defaultValue = "10",required = false) Integer size, Model model){

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
}
