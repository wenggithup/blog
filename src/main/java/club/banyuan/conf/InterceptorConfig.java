package club.banyuan.conf;

import club.banyuan.Interceptor.AdminInterceptor;
import club.banyuan.Interceptor.CommentInterceptor;
import club.banyuan.Interceptor.LoginInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class InterceptorConfig implements WebMvcConfigurer {
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CommentInterceptor()).addPathPatterns("/blogs/*/comments");
        registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/blogs/create","/user/*/admin","/blogs/*/comments");
        registry.addInterceptor(new AdminInterceptor()).addPathPatterns("/user/*/admin");
    }
}
