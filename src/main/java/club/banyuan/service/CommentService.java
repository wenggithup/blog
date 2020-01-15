package club.banyuan.service;

import club.banyuan.bean.Comment;
import club.banyuan.bean.User;
import club.banyuan.dao.CommentDao;
import club.banyuan.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {
    private CommentDao commentDao;
    @Autowired
    public CommentService(CommentDao commentDaoo) {
        this.commentDao=commentDaoo;
    }


    //通过blogid查看评论
    public List<Comment> findCommentByBlogId(Integer blogid){
        return commentDao.getCommentByBlogId(blogid);
    }

    //添加评论
    public void insertCommnet(Comment comment) {
        commentDao.insertComment(comment);
    }

}
