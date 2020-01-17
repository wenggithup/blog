package club.banyuan.Interceptor;

import club.banyuan.bean.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class CommentInterceptor extends HandlerInterceptorAdapter {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        HttpSession session= request.getSession();
        User user=(User) session.getAttribute("CURRENT_USER");
        if(user==null){
            String content=request.getParameter("content");
            session.setAttribute("COMMENT_CONTENT",content);
        }
            return true;

    }
}
