package com.example.lib;

import java.util.ArrayList;

/**
 * the dealer extends the player class.
 */
public class Dealer extends Player {
    private final String NAME = "The Dealer";

    /**
     *
     * @return - the name.
     */
    public String getName() {
        return NAME;
    }

    /**
     * If the second sum in the array is greater than or equal to 17 and less than or equal to 21,
     * then return false. If the first sum in the array is greater than or equal to 17, then return
     * false. else return true.
     * @return - check above.
     */
    public boolean shouldDraw() {
        ArrayList<Integer> sums = getSums();
        if ((sums.get(1) >= 17 && sums.get(1) <=21)|| sums.get(0) >= 17) {
            return false;
        }
        return true;
    }
}
