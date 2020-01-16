package club.banyuan.controller;

import club.banyuan.bean.User;
import club.banyuan.dao.UserDao;
import club.banyuan.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Controller

//处理登陆界面
@RequestMapping("/login")
public class LoginController {
    //自动装配，调用userService中的方法
    @Autowired
    private UserService userService;
    @GetMapping
    String showloginHtml(@RequestParam(required = false) String next,
                         HttpSession session){
        if(next!=null){
            session.setAttribute("NEXT_URI",next);
        }
        return "login";
    }
    /*目的：实现登陆方法，如果用户名正确跳转到user/username界面
    * 功能：判断登陆信息，如果用户名和密码正确，返回user/username界面，否则返回登陆页面
    *       让用户登陆
    * */
    @PostMapping
    String login(@RequestParam String username,
                 @RequestParam String password,
                 HttpSession httpSession) throws UnsupportedEncodingException {
        User user=userService.findUserByName(username);
        //判断用户是否在数据库中，如果存在返回user/username界面，否则返回登陆界面
        if(user!=null&&password.equals(user.getPassword())){
            String next=(String)httpSession.getAttribute("NEXT_URI");
            //将user对象存于session中的CURRENT_USER字段
            httpSession.setAttribute("CURRENT_USER",user);
            if(next!=null){
                return "redirect:"+next;
            }
            //返回user/username界面，注意username是汉字，需要协定编码格式
            return "redirect:/user/"+ URLEncoder.encode(username,"UTF-8");
        }
        //返回登陆界面
        return "redirect:/login";
    }

}
