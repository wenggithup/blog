package club.banyuan.bean;

import javax.xml.crypto.Data;

@lombok.Data
public class Comment {
    private Integer id;
    private String content;
    private Data createdTime;
    private Integer userId;
    private Integer blogId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Data getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Data createdTime) {
        this.createdTime = createdTime;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getBlogId() {
        return blogId;
    }

    public void setBlogId(Integer blogId) {
        this.blogId = blogId;
    }
}
