package com.example.lib;

import java.util.ArrayList;

public class Player {
     private String name;
     private Location location;
     private ArrayList<Card> cards = new ArrayList<>(0);
     private boolean canPlay = true;

    /**
     * Adding a card to the player's hand.
     *
     * @param card - the card given to the player.
     */
     public void addCard(final Card card) {
          cards.add(card);
     }

    /**
     *
     * @return - the name of the player.
     */
     public String getName() {
        return name;
    }

    /**
     *
     * @param i - position of the card in the arraylist.
     * @return - the card at position i in the arraylist.
     */
     public Card getCard(int i) {
          return cards.get(i);
     }

    /**
     *
     * @return - the entire arraylist.
     */
     public ArrayList<Card> getCards() {
          return cards;
     }

    /**
     *
     * @return - the image urls for the cards in a players hand.
     */
     public String[] getUrls() {
         String[] urls = new String[cards.size()];
         for (int i = 0; i < cards.size(); i++) {
             urls[i] = cards.get(i).getUrl();
         }
         return urls;
     }

    /**
     *
     * @return - the location of the player.
     */
    public Location getLocation() {
        return location;
    }

    /*public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }*/

    /**
     * sets the location of the player.
     * @param location - player's location.
     */
    public void setLocation(Location location) {
        this.location = location;
    }

    /**
     *
     * @return - the amount of cards in a hand.
     */
    public int getCardLength() {
          return cards.size();
     }

    /**
     *
     * @return Always returns true.
     */
     public boolean shouldDraw() {
         return true;
     }

    /**
     * gets the sum of a players hand.
     * @return - returns the score of a players hand.
     */
     public ArrayList<Integer> getSums() {
         ArrayList<Integer> sum = new ArrayList<Integer>();
         sum.add(0);
         sum.add(0);
         for (int i = 0; i < getCards().size(); i++) {
             for (int j = 0; j < sum.size(); j++) {
                 sum.set(j, sum.get(j) + getCards().get(i).getValue());
                 if (getCards().get(i).getValue() == 1 && sum.get(0) == sum.get(1)) {
                     sum.set(1,sum.get(1) + 10);
                 }
             }

         }
         return sum;
     }

    /**
     * displays the sum as a string. (basically a toString)
     * @return - the sum of a players hand as as string.
     */
     public String getSumsAsString() {
         String sum = "[";
         ArrayList<Integer> sums = getSums();
         sum += sums.get(0).toString();
         if ((int) sums.get(0) != (int) sums.get(1)) {
             sum += ", ";
             sum += sums.get(1).toString();
         }
         sum += "]";
         return sum;
     }

    /**
     * this function sets whether a player can play or not.
     * @param canPlay whether a player can play.
     */
    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }

    /**
     *
     * @return - whether a player can play or not.
     */
    public boolean canPlay() {
        return canPlay;
    }

    /**
     * sets the name of the player.
     * @param name - the player's name.
     */
    public void setName(String name) {
        this.name = name;
    }
}
