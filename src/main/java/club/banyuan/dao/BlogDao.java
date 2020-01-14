package club.banyuan.dao;

import javax.xml.crypto.Data;

public interface BlogDao  {
    //插入blog
   void insertBlog(Integer id, String title, String content,Integer userId);

}
