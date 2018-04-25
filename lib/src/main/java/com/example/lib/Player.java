package com.example.lib;

import java.util.ArrayList;

public class Player {
     private String name;
     private Location location;
     private ArrayList<Card> cards = new ArrayList<>(0);
     private boolean canPlay = true;
     public void addCard(final Card card) {
          cards.add(card);
     }

    public String getName() {
        return name;
    }

    public Card getCard(int i) {
          return cards.get(i);
     }

     public ArrayList<Card> getCards() {
          return cards;
     }

     public String[] getUrls() {
         String[] urls = new String[cards.size()];
         for (int i = 0; i < cards.size(); i++) {
             urls[i] = cards.get(i).getUrl();
         }
         return urls;
     }

    public Location getLocation() {
        return location;
    }

    public void setCards(ArrayList<Card> cards) {
        this.cards = cards;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public int getCardLength() {
          return cards.size();
     }

     public boolean shouldDraw() {
         return true;
     }

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

     public String getSumsAsString() {
         String sum = "[";
         ArrayList<Integer> sums = getSums();
         sum += sums.get(0).toString();
         if (sums.get(0) != sums.get(1)) {
             sum += ", ";
             sum += sums.get(1).toString();
         }
         sum += "]";
         return sum;
     }

    public void setCanPlay(boolean canPlay) {
        this.canPlay = canPlay;
    }

    public boolean canPlay() {
        return canPlay;
    }

    public void setName(String name) {
        this.name = name;
    }
}
