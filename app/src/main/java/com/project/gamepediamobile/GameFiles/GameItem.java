package com.project.gamepediamobile.GameFiles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GameItem {

    @SerializedName("id")
    @Expose
    private Integer id;
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("released")
    @Expose
    private String released;
    @SerializedName("background_image")
    @Expose
    private String backgroundImage;
    @SerializedName("background_image_additional")
    @Expose
    private String backgroundImageAdditional;
    @SerializedName("metacritic")
    @Expose
    private Integer metacritic;
    @SerializedName("rating")
    @Expose
    private Double rating;
    @SerializedName("playtime")
    @Expose
    private Integer playtime;
    @SerializedName("description")
    @Expose
    private String description;

    public GameItem(Integer id, String name, String released, String backgroundImage, Integer metacritic, Double rating, Integer playtime, String description) {
        this.id = id;
        this.name = name;
        this.released = released;
        this.backgroundImage = backgroundImage;
        this.metacritic = metacritic;
        this.rating = rating;
        this.playtime = playtime;
        this.description = description;
    }

    public GameItem() {
        super();
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleased() {
        return released;
    }

    public void setReleased(String released) {
        this.released = released;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getBackgroundImageAdditional() {
        return backgroundImageAdditional;
    }

    public void setBackgroundImageAdditional(String backgroundImageAdditional) {
        this.backgroundImageAdditional = backgroundImageAdditional;
    }

    public Integer getMetacritic() {
        return metacritic;
    }

    public void setMetacritic(Integer metacritic) {
        this.metacritic = metacritic;
    }

    public Double getRating() {
        return rating;
    }

    public void setRating(Double rating) {
        this.rating = rating;
    }

    public Integer getPlaytime() {
        return playtime;
    }

    public void setPlaytime(Integer playtime) {
        this.playtime = playtime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static class Screenshot {
        @SerializedName("id")
        @Expose
        private Integer id;

        @SerializedName("image")
        @Expose
        private String image;

        public Screenshot(Integer id, String image) {
            this.id = id;
            this.image = image;
        }

        public Screenshot() {
            super();
        }

        public Integer getId() {
            return id;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public String getImage() {
            return image;
        }

        public void setImage(String image) {
            this.image = image;
        }
    }

    @SerializedName("results")
    @Expose
    private List<Screenshot> screenshots;

    public List<String> getScreenshots() {
        List<String> images = new ArrayList<>();
        if (screenshots != null) {
            for (Screenshot screenshot : screenshots) {
                images.add(screenshot.getImage());
            }
        }
        return images;
    }

    public void setScreenshots(List<Screenshot> screenshots) {
        this.screenshots = screenshots;
    }
}
