package com.example.lib;

import java.util.HashMap;

public class Deck {
   private String deckID;
   private int cardsRemaining;
   private static final int DIFFERENT_CARDS = 13;
   private int[] cards = new int[DIFFERENT_CARDS];

    /**
     * Constructor
     * @param deckID - sets the deckId to be used by the Deck class for the game.
     */
   public Deck(String deckID) {
       setDeckID(deckID);
   }

    /**
     *
     * @return - gets the deck ID.
     */
   public String getDeckID() {
       return deckID;
   }

    /**
     * sets the deck ID.
     * @param deckID
     */
   public void setDeckID(String deckID) {
       this.deckID = deckID;
       initializeCards();
   }

    /**
     * puts four cards in the integer array of cards.
     */
   private void initializeCards() {
       for (int i = 0; i < cards.length; i++) {
           cards[i] = 4;
       }
   }

    /**
     * hashMap which find the position of the card in the card array.
     */
   static HashMap<String, Integer> nameToValue;
   static {
       nameToValue = new HashMap<String, Integer>(0);
       nameToValue.put("A", 0);
       nameToValue.put("2", 1);
       nameToValue.put("3", 2);
       nameToValue.put("4", 3);
       nameToValue.put("5", 4);
       nameToValue.put("6", 5);
       nameToValue.put("7", 6);
       nameToValue.put("8", 7);
       nameToValue.put("9", 8);
       nameToValue.put("0", 9);
       nameToValue.put("J", 10);
       nameToValue.put("Q", 11);
       nameToValue.put("K", 12);
   }

    /*public HashMap<String, Integer> getNameToValue() {
        return nameToValue;
    }*/

    /**
     * get Cards.
     * @return the cards left in the deck.
     */
    public int[] getCards() {
        return cards;
    }

    /**
     * removes the card from the deck.
     * @param key - the key in the hash map.
     */
    public void removeCard(String key) {
       int pos = nameToValue.get(key);
       cards[pos]--;
    }

    /**
     *
     * @return - finds the number of cards remaining in the deck.
     */
    public int getCardsRemaining() {
        return cardsRemaining;
    }

    /**
     * sets the cards remaining in the deck.
     * @param cardsRemaining - cards remaining in the deck.
     */
    public void setCardsRemaining(int cardsRemaining) {
        this.cardsRemaining = cardsRemaining;
    }
}
