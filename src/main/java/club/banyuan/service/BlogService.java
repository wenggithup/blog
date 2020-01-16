package club.banyuan.service;

import club.banyuan.bean.Blog;
import club.banyuan.dao.BlogDao;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BlogService {
    private BlogDao blogDao;

    @Autowired

    public BlogService(BlogDao blogDao) {
        this.blogDao = blogDao;
    }

    public List<Blog> findBlogByUsername(String name) {
        return blogDao.selectBlogByUsername(name);
    }

    public List<Blog> findBlogByUsernameWithPageInfo(String username, Integer page, Integer size) {
//         offset=(limit-1)*10;
        Integer offset = (page - 1) * size;
        return blogDao.selectBlogByUsernameWithPageInfo(username, offset, size);
    }

    public PageInfo getUserBlog(String name, Integer page, Integer size) {
        //使用PageHelper中的方法自定义
        PageHelper.startPage(page, size);
        //难道dao层的数据
        List<Blog> allBlogs = blogDao.selectBlogByUsername(name);
        //使用pageInfo返回
        return new PageInfo(allBlogs);
    }

    //通过博客id展示 内容
    public Blog findBlogById(Integer id) {
        return blogDao.selectBlogByid(id);
    }

    //插入blog用户
    public void insertBlogs(Blog blog) {
        blogDao.insetBlog(blog);
    }

}
