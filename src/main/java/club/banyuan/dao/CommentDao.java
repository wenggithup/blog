package club.banyuan.dao;

import club.banyuan.bean.Comment;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentDao {
    //通过blogsid展示评论
    public List<Comment> getCommentByBlogId(Integer blogid);

    //添加评论
    void insertComment(Comment comment);
}
