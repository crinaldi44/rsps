package io.ruin.api.protocol.world;

public enum WorldType {
    ECO("RuneScape", "https://kronos.rip"),
    BETA("RuneScape", "https://kronos.rip"),
    PVP("RuneScape", "https://kronos.rip"),
    DEV("RuneScape", "http://127.0.0.1");

    WorldType(String worldName, String websiteUrl) {
        this.worldName = worldName;
        this.websiteUrl = websiteUrl;
    }

    private String worldName, websiteUrl;

    public String getWorldName() {
        return worldName;
    }

    public String getWebsiteUrl() { return websiteUrl; }
}