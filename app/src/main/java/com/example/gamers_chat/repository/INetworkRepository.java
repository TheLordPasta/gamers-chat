package com.example.gamers_chat.repository;

import androidx.lifecycle.MutableLiveData;


import com.example.gamers_chat.models.GameModel;

import java.util.ArrayList;

public interface INetworkRepository {
    MutableLiveData<ArrayList<GameModel>> getGames();
}
