package com.example.gamers_chat.services;

import android.os.StrictMode;

import com.example.gamers_chat.models.GameModel;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import org.json.*;

public class DataService {

    private static ArrayList<GameModel> arrGames = new ArrayList<>();

    public static ArrayList<GameModel> getArrGames() {

        String sURL = "https://www.freetogame.com/api/games";
        URL url;
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);

        try
        {

            try {
                url = new URL(sURL);
            }
            catch (MalformedURLException e)
            {
                throw new RuntimeException(e);
            }

            HttpURLConnection request = null;

            try{
                request = (HttpURLConnection) url.openConnection();
                request.connect();
                JsonParser jp = new JsonParser();
                JsonElement root = jp.parse(new InputStreamReader((InputStream) request.getContent()));

                JsonArray rootObj = root.getAsJsonArray();

                for (JsonElement js : rootObj){

                    JsonObject obj = js.getAsJsonObject();

                    JsonElement entryID = obj.get("id");
                    JsonElement entryTitle = obj.get("title");
                    JsonElement entryThumbnail = obj.get("thumbnail");
                    JsonElement entryDescription = obj.get("short_description");
                    JsonElement entryGameURL = obj.get("game_url");
                    JsonElement entryGenre = obj.get("genre");
                    JsonElement entryPlatform = obj.get("platform");
                    JsonElement entryPublisher = obj.get("publisher");
                    JsonElement entryDeveloper = obj.get("developer");
                    JsonElement entryReleaseDate = obj.get("release_date");

                    String id = entryID.toString().replace("\"","");

                    String title = entryTitle.toString().replace("\"","");

                    String thumbnail = null;
                    if (entryThumbnail != null) {
                        thumbnail = entryThumbnail.toString().replace("\"","");
                    } else entryThumbnail = null;

                    String description = entryDescription.toString().replace("\"","");

                    String gameURL = entryGameURL.toString().replace("\"","");

                    String genre = entryGenre.toString().replace("\"","");

                    String platform = entryPlatform.toString().replace("\"","");

                    String publisher = entryPublisher.toString().replace("\"","");

                    String developer = entryDeveloper.toString().replace("\"","");

                    String releaseDate = entryReleaseDate.toString().replace("\"","");

                    GameModel gm = new GameModel();

                    gm.id = Integer.parseInt(id);
                    gm.title = title;
                    gm.thumbnail = thumbnail;
                    gm.short_description = description;
                    gm.game_url = gameURL;
                    gm.genre = genre;
                    gm.platform = platform;
                    gm.publisher = publisher;
                    gm.developer = developer;
                    gm.release_date = releaseDate;

                    arrGames.add(gm);
                }
            }
            catch (IOException e){
                throw new IOException(e);
            }



        }
        catch (IOException e)
        {
            throw new RuntimeException(e);
        }

        return arrGames;
    }


}
