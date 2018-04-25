package com.example.lib;

import java.util.HashMap;

public class Deck {
   private String deckID;
   private int cardsRemaining;

   private int[] cards = new int[13];

   public Deck(String deckID) {
       setDeckID(deckID);
   }
   public String getDeckID() {
       return deckID;
   }

   public void setDeckID(String deckID) {
       this.deckID = deckID;
       initializeCards();
   }

   private void initializeCards() {
       for (int i = 0; i < cards.length; i++) {
           cards[i] = 4;
       }
   }

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

    public HashMap<String, Integer> getNameToValue() {
        return nameToValue;
    }

    public int[] getCards() {
        return cards;
    }

    public void removeCard(String key) {
       int pos = nameToValue.get(key);
       cards[pos]--;
    }

    public int getCardsRemaining() {
        return cardsRemaining;
    }

    public void setCardsRemaining(int cardsRemaining) {
        this.cardsRemaining = cardsRemaining;
    }
}
