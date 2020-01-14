package club.banyuan.bean;

import javax.xml.crypto.Data;
@lombok.Data
public class Blog {
    private Integer id;
    private String title;
    private String content;
    private Data createdTime;
    private Integer userId;

}
