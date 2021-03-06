package club.banyuan.bean;

import jdk.nashorn.internal.objects.annotations.Constructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;
@lombok.Data

public class Blog {
    private Integer id;
    private String title;
    private String content;
    private Data createdTime;
    private Integer userId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
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
}
