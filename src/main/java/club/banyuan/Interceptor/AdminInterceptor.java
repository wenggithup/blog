package club.banyuan.Interceptor;

import club.banyuan.bean.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.net.URLEncoder;

public class AdminInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //判断浏览器中的name和session中的是否相同
        //拿到浏览器中的uriname
        String str = request.getRequestURI();
        String[] s1 = str.split("/");
        String uriname = URLDecoder.decode(s1[2], "UTF-8");

        //拿到session中的sessionName
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("CURRENT_USER");
        String sessionName = user.getName();

        //比较两个name是否相同，如果相同，返回true，进入此账户的admin，否则返回uriname中的admin地址
        if (sessionName.equals(uriname)) {
            return true;
        } else {

            //要注意中文的编码格式，否则会重定向出错
            response.sendRedirect("/user/" + URLEncoder.encode(sessionName, "UTF-8") + "/admin");
            return false;
        }
    }
}
