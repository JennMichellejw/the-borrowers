package com.jennmichelle.theborrowers1;

public enum ItemCondition {
    MINT("Mint"),
    GOOD("Good"),
    FAIR("Fair"),
    BAD("Bad");

    private final String displayName;

    ItemCondition(String displayName) {
        this.displayName = displayName;
    }

    public String getDisplayName() {
        return displayName;
    }
}
