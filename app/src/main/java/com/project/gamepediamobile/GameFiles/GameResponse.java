package com.project.gamepediamobile.GameFiles;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class GameResponse {
    @SerializedName("results")
    @Expose
    private List<Game> games;

    public List<Game> getGames() {
        return games;
    }

    public void setGames(List<Game> games) {
        this.games = games;
    }


    public static class Screenshots {
        @SerializedName("results")
        @Expose
        private List<ScreenshotItem> screenshots;

        public List<String> getScreenshots() {
            List<String> images = new ArrayList<>();
            if (screenshots != null) {
                for (ScreenshotItem screenshot : screenshots) {
                    images.add(screenshot.getImage());
                }
            }
            return images;
        }
        public void setScreenshots(List<ScreenshotItem> screenshots) {
            this.screenshots = screenshots;
        }
    }
}