package com.example.lib;

import java.util.ArrayList;
import java.util.Random;

public class Computer extends Player {
    private String name = "Computer";

    public String getName() {
        return name;
    }

    public boolean shouldDraw(Deck deck) {
        ArrayList<Integer> sum = getSums();
        if (sum.get(0) == 21 || sum.get(1) == 21) {
            return false;
        }
        if (21 - sum.get(1) >= 3) {
            return false;
        }
        if (sum.get(0) > 21) {
            return false;
        }
        int totalValue = 0;
        int difference = 21 - sum.get(0);
        int numCardsUnderValue = 0;
        for (int i = 0; i < deck.getCards().length; i++) {
            if (i >= 10) {
                if (10 < difference) {
                    numCardsUnderValue += deck.getCards()[i];
                }
            } else if (i + 1 < difference) {
                numCardsUnderValue += deck.getCards()[i];
            }
        }
        for (int i = 0; i < deck.getCards().length; i++) {
            if (i >= 10) {
                totalValue += 10;
            } else {
                totalValue += (i + 1);
            }
        }
        double averageValue = totalValue / deck.getCardsRemaining();
        double modifier = difference / averageValue;
        double probability = numCardsUnderValue * modifier / deck.getCardsRemaining();
        Random random = new Random();
        double randomNumber = random.nextDouble();
        if (randomNumber <= probability) {
            return true;
        }
        return false;
    }
}
