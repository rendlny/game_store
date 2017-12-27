package REST_Steam_news;

import java.util.HashMap;
import java.util.Map;

public class Newsitem {

    private String gid;
    private String title;
    private String url;
    private Boolean isExternalUrl;
    private String author;
    private String contents;
    private String feedlabel;
    private Integer date;
    private String feedname;

    public Newsitem() {
    }

    /**
     *
     * @param author
     * @param title
     * @param feedname
     * @param contents
     * @param gid
     * @param date
     * @param feedlabel
     * @param url
     * @param isExternalUrl
     */
    public Newsitem(String gid, String title, String url, Boolean isExternalUrl, String author, String contents, String feedlabel, Integer date, String feedname) {
        super();
        this.gid = gid;
        this.title = title;
        this.url = url;
        this.isExternalUrl = isExternalUrl;
        this.author = author;
        this.contents = contents;
        this.feedlabel = feedlabel;
        this.date = date;
        this.feedname = feedname;
    }

    public String getGid() {
        return gid;
    }

    public void setGid(String gid) {
        this.gid = gid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Boolean getIsExternalUrl() {
        return isExternalUrl;
    }

    public void setIsExternalUrl(Boolean isExternalUrl) {
        this.isExternalUrl = isExternalUrl;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getContents() {
        return contents;
    }

    public void setContents(String contents) {
        this.contents = contents;
    }

    public String getFeedlabel() {
        return feedlabel;
    }

    public void setFeedlabel(String feedlabel) {
        this.feedlabel = feedlabel;
    }

    public Integer getDate() {
        return date;
    }

    public void setDate(Integer date) {
        this.date = date;
    }

    public String getFeedname() {
        return feedname;
    }

    public void setFeedname(String feedname) {
        this.feedname = feedname;
    }

    @Override
    public String toString() {
        return "Newsitem{" + "gid=" + gid + ", title=" + title + ", url=" + url + ", isExternalUrl=" + isExternalUrl + ", author=" + author + ", contents=" + contents + ", feedlabel=" + feedlabel + ", date=" + date + ", feedname=" + feedname + '}';
    }

}
