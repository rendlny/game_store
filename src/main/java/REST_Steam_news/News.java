package REST_Steam_news;

import java.util.HashMap;
import java.util.Map;

public class News {

    private Appnews appnews;

    public News() {
    }

    /**
     *
     * @param appnews
     */
    public News(Appnews appnews) {
        super();
        this.appnews = appnews;
    }

    public Appnews getAppnews() {
        return appnews;
    }

    public void setAppnews(Appnews appnews) {
        this.appnews = appnews;
    }

    @Override
    public String toString() {
        return "News{" + "appnews=" + appnews + '}';
    }

}
