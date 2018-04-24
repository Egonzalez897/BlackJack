package com.example.lib;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Deck deck;
    private Player previousPlayer;

    public Game(final ArrayList<Player> players, final Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    public void reorderArray() {
        Collections.shuffle(players);
        while (!(players.get(0) instanceof Dealer)) {
            players.add(players.get(0));
            players.remove(0);
        }
        setCurrentPlayer(players.get(1));
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public Deck getDeck() {
        return deck;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void start() {
        currentPlayer = players.get(1);
    }


}
