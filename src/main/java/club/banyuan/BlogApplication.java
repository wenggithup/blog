package club.banyuan;

import club.banyuan.bean.Blog;
import club.banyuan.bean.User;
import club.banyuan.dao.BlogDao;
import club.banyuan.dao.UserDao;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
@MapperScan("club.banyuan.dao")
@SpringBootApplication
public class BlogApplication {
    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(BlogApplication.class, args);
        /*UserDao userDao=context.getBean(UserDao.class);
        for (int i = 1; i < 100; i++) {
            userDao.insertUser(i,"张三"+i,"zhangsan"+i,"zhangsan@"+i*i);
        }*/
        UserDao userDao= context.getBean(UserDao.class);
        System.out.println(userDao.selectUserByName("张三5").toString());
       BlogDao blog =context.getBean(BlogDao.class);
        /*for (int i = 102; i < 200; i++) {
            blog.insertBlog(i,"这是标题"+i,"这是博客内容"+i+" "+i,99);
        }*/
        BlogDao blogDao=context.getBean(BlogDao.class);
        System.out.println(blogDao.selectBlogByUsername("张三6"));

    }
}
