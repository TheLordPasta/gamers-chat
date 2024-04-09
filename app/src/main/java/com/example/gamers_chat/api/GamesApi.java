package com.example.gamers_chat.api;



import com.example.gamers_chat.models.GameModel;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.GET;

public interface GamesApi {
    @GET("games")
    Call<ArrayList<GameModel>> getGames();
}
