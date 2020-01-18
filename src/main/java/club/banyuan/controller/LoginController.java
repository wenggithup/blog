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
                         HttpSession session) {
        //查看请求的页面中是否有next参数
        if (next != null) {
            session.setAttribute("NEXT_URI", next);
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
        User user = userService.findUserByName(username);
        //判断用户是否在数据库中，如果存在返回user/username界面，否则返回登陆界面
        if (user != null && password.equals(user.getPassword())) {
            //取出session中NEXT_URI字段的值
            String next = (String) httpSession.getAttribute("NEXT_URI");
            //将user对象存于session中的CURRENT_USER字段
            httpSession.setAttribute("CURRENT_USER", user);
            if (next != null) {
                //含有中文的Path注意要转换编码格式，否则链接会乱码
                String[] uri = next.split("/");
                String redirectUrl = "";
                for (String i : uri) {
                    if (i.length() > 0) {
                        redirectUrl += "/" + URLEncoder.encode(i, "UTF-8");
                    }
                }
                httpSession.removeAttribute("NEXT_URI");
                return "redirect:" + redirectUrl;
            }
            //返回user/username界面，注意username是汉字，需要限定编码格式
            return "redirect:/user/" + URLEncoder.encode(username, "UTF-8");
        }
        //返回登陆界面
        return "redirect:/login";
    }

}
