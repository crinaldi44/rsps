package net.runelite.standalone;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor @Getter
public enum CustomWorldType {

    PVP("RuneScape", "https://kronos.rip", "144.217.10.42", "144.217.10.42"),
    ECO("RuneScape", "https://kronos.rip", "144.217.10.42", "144.217.10.42"),
    BETA("RuneScape", "167.114.217.217", "167.114.217.217", "144.217.10.42"),
    DEV("RuneScape", "127.0.0.1", "127.0.0.1", "127.0.0.1");
//    DEV("RuneScape", "26.100.41.243", "26.100.41.243", "26.100.41.243");

    private final String name;
    private final String url;
    private final String gameServerAddress;
    private final String fileServerAddress;

}
