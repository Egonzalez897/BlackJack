package com.example.lib;


import java.util.HashMap;

public class Card {
    /**
     * this value is the value of the card.
     */
    private int value;
    private String url;

    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * sets the value of the card.
     * @param value - sets the value.
     */
    public void setValue(int value) {
        this.value = value;
    }

    /**
     * gets the value of the class.
     * @return - value.
     */
    public int getValue() {
        return value;
    }

    /**
     * gets the URL.
     * @return ^.
     */
    public String getUrl() {
        return url;
    }

    /**
     * Maps the card to the value.
     */
    public static HashMap<String, Integer> values;
    static {
        values = new HashMap<String, Integer>();
        for (int i = 2; i < 10; i++) {
            values.put(i + "", i);
        }
        values.put("A", 1);
        values.put("0", 10);
        values.put("J", 10);
        values.put("Q", 10);
        values.put("K", 10);
    }

    /**
     *
     * @param url - the url for the string for the card.
     * @param value - the value of the card.
     */
    public Card(String url, int value) {
        this.url = url;
        this.value = value;


    }

}
