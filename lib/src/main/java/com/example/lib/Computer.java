package com.example.lib;

import java.util.ArrayList;
import java.util.Random;

public class Computer extends Player {
    private String name = "Computer";
    private boolean difficult = false;

    /**
     * name of the computer.
     * @return - the name of the computer.
     */
    public String getName() {
        return name;
    }

    /**
     * if game is easy, then computers will act like dealer.
     * Else, computers calculate probabilities.
     * @param difficult - if true, computers are difficult. Else act like dealers.
     */
    public void setDifficult(boolean difficult) {
        this.difficult = difficult;
    }

    /**
     * Whether or not computer should draw. Calculates the probablity of winning the game by
     * drawing another card.
     * @param deck - the deck used in the game.
     * @return - whether the computer should draw.
     */
    public boolean shouldDraw(Deck deck) {
        ArrayList<Integer> sum = getSums();
        if (sum.get(0) == 21 || sum.get(1) == 21) {
            return false;
        }
        if (sum.get(1) >= 20 && sum.get(1) <= 21) {
            return false;
        }
        if (sum.get(0) > 21) {
            return false;
        }
        if (difficult) {
            int totalValue = 0;
            // value needed to get score of 21.
            int difference = 21 - sum.get(0);
            int numCardsUnderValue = 0;
            for (int i = 0; i < deck.getCards().length; i++) {
                if (i >= 10 && 10 < difference) {
                    numCardsUnderValue += deck.getCards()[i];
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
            return randomNumber <= probability;
        } else {
            return (getSums().get(0) < 17);
        }
    }
}
