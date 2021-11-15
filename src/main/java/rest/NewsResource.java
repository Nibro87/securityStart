package rest;


import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import entities.DTO.ArticleDTO;
import facades.FacadeExample;
import utils.EMF_Creator;
import utils.Env;
import utils.HttpUtils;

import javax.persistence.EntityManagerFactory;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.io.IOException;
import java.util.List;

@Path("news")
public class NewsResource {

    private static final EntityManagerFactory EMF = EMF_Creator.createEntityManagerFactory();

    private static final FacadeExample FACADE = FacadeExample.getFacadeExample(EMF);
    private static final Gson GSON = new GsonBuilder().setPrettyPrinting().create();

    @GET
    @Produces({MediaType.APPLICATION_JSON})
    public String demo() {
        return "{\"msg\":\"News\"}";
    }



    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("headlines")
    public String getNews() throws IOException {
        String url = "https://newsapi.org/v2/top-headlines?country=us&apiKey=" +Env.APIKEY;
       String headlines = HttpUtils.fetchData(url);
       ArticleDTO articleDTO = GSON.fromJson(headlines,ArticleDTO.class);


    return headlines;
}


}