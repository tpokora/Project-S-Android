package projects.tpokora.com.project_s_android.rest.model;

import java.util.Date;

/**
 * Created by pokor on 26.06.2016.
 */
public class Article extends AbstractEntity {

    private String title;
    private String content;
    private Date createTime;
    private String author;

    public Article() {

    }

    public Article(Integer id, String title, String content, Date createTime) {
        super(id);
        this.title = title;
        this.content = content;
        this.createTime = createTime;
    }

    public Article(Integer id, String title, String content, Date createTime, String author) {
        super(id);
        this.title = title;
        this.content = content;
        this.createTime = createTime;
        this.author = author;
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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }
}
