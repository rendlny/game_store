package REST_Steam_news;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Appnews {

    private Integer appid;

    private List<Newsitem> newsitems = null;

    public Appnews() {
    }

    /**
     *
     * @param newsitems
     * @param appid
     */
    public Appnews(Integer appid, List<Newsitem> newsitems) {
        super();
        this.appid = appid;
        this.newsitems = newsitems;
    }

    public Integer getAppid() {
        return appid;
    }

    public void setAppid(Integer appid) {
        this.appid = appid;
    }

    public List<Newsitem> getNewsitems() {
        return newsitems;
    }

    public void setNewsitems(List<Newsitem> newsitems) {
        this.newsitems = newsitems;
    }

    @Override
    public String toString() {
        return "Appnews{" + "appid=" + appid + ", newsitems=" + newsitems + '}';
    }
    
}
