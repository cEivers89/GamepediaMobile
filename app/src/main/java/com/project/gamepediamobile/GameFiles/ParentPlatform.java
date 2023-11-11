package com.project.gamepediamobile.GameFiles;

public class ParentPlatform {
    private Platform platform;

    public ParentPlatform(Platform platform) {
        this.platform = platform;
    }

    public Platform getPlatform() {
        return platform;
    }

    public void setPlatform(Platform platform) {
        this.platform = platform;
    }

    @Override
    public String toString() {
        return platform != null ? platform.getName() : "Unknown";
    }

    public static class Platform {
        private int id;
        private String name;

        public Platform(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        @Override
        public String toString() {
            return name != null ? name : "Unknown";
        }
    }
}
