package cn.superhh.blogpro.pojo;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

public class Blog implements Serializable {
    private Integer id;
    private String title;
    private String summary;
    private LocalDateTime releaseDate;
    private int clickHit;
    private int replyHit;
    private String content;
    private BlogType blogType;
    private String keyWord;
      private String releaseDateStr;
           private  Integer blogCount;
           private List<String> imagesList;
           private String ContentNoTag;

    public String getContentNoTag() {
        return ContentNoTag;
    }

    public void setContentNoTag(String contentNoTag) {
        ContentNoTag = contentNoTag;
    }

    public LocalDateTime getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDateTime releaseDate) {
        this.releaseDate = releaseDate;
    }

    public Integer getBlogCount() {
        return blogCount;
    }

    public void setBlogCount(Integer blogCount) {
        this.blogCount = blogCount;
    }

    public String getReleaseDateStr() {
        return releaseDateStr;
    }

    public void setReleaseDateStr(String releaseDateStr) {
        this.releaseDateStr = releaseDateStr;
    }

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

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }



    public int getClickHit() {
        return clickHit;
    }

    public void setClickHit(int clickHit) {
        this.clickHit = clickHit;
    }

    public int getReplyHit() {
        return replyHit;
    }

    public void setReplyHit(int replyHit) {
        this.replyHit = replyHit;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public BlogType getBlogType() {
        return blogType;
    }

    public void setBlogType(BlogType blogType) {
        this.blogType = blogType;
    }

    public String getKeyWord() {
        return keyWord;
    }

    public void setKeyWord(String keyWord) {
        this.keyWord = keyWord;
    }

    public List<String> getImagesList() {
        return imagesList;
    }

    public void setImagesList(List<String> imagesList) {
        this.imagesList = imagesList;
    }

    @Override
    public String toString() {
        return "Blog{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", releaseDate=" + releaseDate +
                ", clickHit=" + clickHit +
                ", replyHit=" + replyHit +
                ", content='" + content + '\'' +
                ", blogType=" + blogType +
                ", keyWord='" + keyWord + '\'' +
                ", releaseDateStr='" + releaseDateStr + '\'' +
                ", blogCount=" + blogCount +
                ", imagesList=" + imagesList +
                ", ContentNoTag='" + ContentNoTag + '\'' +
                '}';
    }
}
