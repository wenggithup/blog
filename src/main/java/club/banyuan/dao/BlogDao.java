package club.banyuan.dao;

import club.banyuan.bean.Blog;
import org.springframework.stereotype.Repository;

import javax.xml.crypto.Data;
import java.util.List;
@Repository
public interface BlogDao  {
    //插入blog
   void insertBlog(Integer id, String title, String content,Integer userId);
   //通过姓名查询blog
   List<Blog> selectBlogByUsername(String username);
    //分页功能
   List<Blog> selectBlogByUsernameWithPageInfo(String username, Integer offset, Integer limit);
   //通过blog id展示blog
   Blog selectBlogByid(Integer id);
   //添加博客内容
   void insetBlog(Blog blog);


}
