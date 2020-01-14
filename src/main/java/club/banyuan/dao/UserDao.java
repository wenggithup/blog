package club.banyuan.dao;

import club.banyuan.bean.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao {

   //插入user
   void insertUser(Integer id,String name,String password,String email);
   //通过name查询user表内容
   User selectUserByName(String name);
}
