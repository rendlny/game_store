/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package REST_Steam_news;

import com.google.gson.Gson;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author Ren
 */
public class RootObject {
    public Appnews appnews;

    public Appnews getAppnews() {
        return appnews;
    }

    public void setAppnews(Appnews appnews) {
        this.appnews = appnews;
    }

    @Override
    public String toString() {
        return "RootObject{" + "appnews=" + appnews + '}';
    }
    
    public RootObject getNews(int appid){
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://api.steampowered.com/ISteamNews/GetNewsForApp/v0002/?count=4&maxlength=800&formate=json")
                .queryParam("appid", appid);
        
        String json = target.request(MediaType.APPLICATION_JSON).get(String.class);
        Gson gson = new Gson();
        RootObject news = gson.fromJson(json, RootObject.class);
        
        return news;
    }
}
