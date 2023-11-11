package com.project.gamepediamobile.GameFiles;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class GameInfo {
    private int id;
    private String name;
    private String description;
    private int metacritic;
    private String released;
    @SerializedName("background_image")
    private String backgroundImage;
    @SerializedName("background_image_additional")
    private String backgroundImageAdditional;
    private double rating;
    private int playtime;
    @SerializedName("parent_platforms")
    private List<ParentPlatform> parentPlatforms;
    private List<Genre> genres;

    public GameInfo(int id, String name, String description, int metacritic, String released, String backgroundImage,
                    String backgroundImageAdditional, double rating, int playtime, List<ParentPlatform> parentPlatforms, List<Genre> genres) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.metacritic = metacritic;
        this.released = released;
        this.backgroundImage = backgroundImage;
        this.backgroundImageAdditional = backgroundImageAdditional;
        this.rating = rating;
        this.playtime = playtime;
        this.parentPlatforms = parentPlatforms;
        this.genres = genres;
    }

    public Integer id() { return id; }

    public void setId(int id) { this.id = id; }

    public String getName() { return name; }

    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public int getMetacritic() { return metacritic; }

    public void setMetacritic(int metacritic) { this.metacritic = metacritic; }

    public String getReleased() { return released; }

    public void setReleased(String released) { this.released = released; }

    public String getBackgroundImage() { return backgroundImage; }

    public void setBackgroundImage(String backgroundImage) { this.backgroundImage = backgroundImage; }

    public String getBackgroundImageAdditional() { return backgroundImageAdditional; }

    public void setBackgroundImageAdditional(String backgroundImageAdditional) { this.backgroundImageAdditional = backgroundImageAdditional; }

    public double getRating() { return rating; }

    public void setRating(double rating) { this.rating = rating; }

    public int getPlaytime() { return playtime; }

    public void setPlaytime(int playtime) { this.playtime = playtime; }

    public List<ParentPlatform> getParentPlatforms() { return parentPlatforms; }

    public void setParentPlatforms(List<ParentPlatform> parentPlatforms) { this.parentPlatforms = parentPlatforms; }

    public List<Genre> getGenres() { return genres; }

    public void setGenres(List<Genre> genres) { this.genres = genres; }


}
