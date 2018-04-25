package com.example.lib;

public class Dealer extends Player {
    private String name = "The Dealer";

    public String getName() {
        return name;
    }

    public boolean shouldDraw() {
        if (getSums().get(1) >= 17) {
            return false;
        } else {
            return true;
        }
    }
}
