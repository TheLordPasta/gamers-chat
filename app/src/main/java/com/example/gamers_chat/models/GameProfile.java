package com.example.gamers_chat.models;

public class GameProfile {

    private String gameName;
    private String description;
    private String publisher;
    private String platform;
    private int bannerImage;

    public GameProfile(String gameName, String description, String publisher, String platform, int bannerImage) {
        this.gameName = gameName;
        this.description = description;
        this.publisher = publisher;
        this.platform = platform;
        this.bannerImage = bannerImage;
    }

    public void setGameName(String gameName) {
        this.gameName = gameName;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public void setBannerImage(int bannerImage) {
        this.bannerImage = bannerImage;
    }

    public String getGameName() {
        return gameName;
    }

    public String getDescription() {
        return description;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPlatform() {
        return platform;
    }

    public int  getBannerImage() {
        return bannerImage;
    }
}









