package com.example.gamers_chat.models;

import java.util.List;

public class UserProfile {

    private String nickName;
    private String bio;
    private int profilePhoto; // Integer
    private List<GameModel> favoriteGames;

    public UserProfile(String nickName, String bio, int profilePhoto, int id_, List<GameModel> favoriteGames) {
        this.nickName = nickName;
        this.bio = bio;
        this.profilePhoto = profilePhoto;
        this.favoriteGames = favoriteGames;
    }

    public void setNickName(String name) {
        this.nickName = name;
    }

    public void setBio(String price) {
        this.bio = price;
    }

    public void setProfilePhoto(int image) {
        this.profilePhoto = image;
    }

    public String getNickName() {
        return nickName;
    }

    public String getBio() {
        return bio;
    } 

    public int getProfilePhoto() {
        return profilePhoto;
    }

    public List<GameModel> getFavoriteGames() {
        return favoriteGames;
    }
}








