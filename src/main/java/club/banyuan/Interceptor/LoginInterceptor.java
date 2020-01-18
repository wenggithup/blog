package club.banyuan.Interceptor;

import club.banyuan.bean.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class LoginInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //取出session中的值
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("CURRENT_USER");
        //判断session里面有没有用户，如果有，返回true
        if (user != null) {
            return true;
        } else {
            //如果没有用户，返回false并返回之前的url值
            String uri = "/login?next=" + request.getRequestURI();
            response.sendRedirect(uri);
            return false;
        }
    }
}
