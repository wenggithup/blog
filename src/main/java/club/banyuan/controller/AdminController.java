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
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class AdminController {
    @Autowired
    private BlogService blogService;

    @GetMapping("user/{username}/admin")
    String getAdimn(@PathVariable String username, Model model,
                    @RequestParam(defaultValue = "1", required = false) Integer page,
                    @RequestParam(defaultValue = "10", required = false) Integer size) {

        PageInfo blogs = blogService.getUserBlog(username, page, size);
        System.out.println(blogs);
        model.addAttribute("blogs", blogs);
        model.addAttribute("username",username);
        return "admin";
    }

}
