package com.example.lib;

import java.util.ArrayList;

public class Dealer extends Player {
    private String name = "The Dealer";

    public String getName() {
        return name;
    }

    public boolean shouldDraw() {
        ArrayList<Integer> sums = getSums();
        if ((sums.get(1) >= 17 && sums.get(1) <=21)|| sums.get(0) >= 17) {
            return false;
        }
        return true;
    }
}
