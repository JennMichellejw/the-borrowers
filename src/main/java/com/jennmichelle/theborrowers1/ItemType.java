package com.jennmichelle.theborrowers1;

public enum ItemType {
    MUSICEQUIPMENT("Music Equipment"),
    MEDIA("Games, Movies, Music"),
    GAMECONSOLES("Game System"),
    TABLETOPGAME("Table-Top Games"),
    TOOL("Tool"),
    VEHICLE("Vehicle"),
    OTHER("Other");

    private final String displayName;

    ItemType(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}

