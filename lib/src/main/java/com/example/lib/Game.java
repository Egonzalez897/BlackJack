package com.example.lib;

import java.util.ArrayList;
import java.util.Collections;

public class Game {
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Deck deck;

    /**
     * Constructor.
     * @param players - players in the game.
     * @param deck - the deck object for the current game.
     */
    public Game(final ArrayList<Player> players, final Deck deck) {
        this.players = players;
        this.deck = deck;
    }

    /**
     * This reorders the player array and makes the dealer the first player. Every time.
     */
    public void reorderArray() {
        Collections.shuffle(players);
        while (!(players.get(0) instanceof Dealer)) {
            players.add(players.get(0));
            players.remove(0);
        }
        setCurrentPlayer(players.get(1));
    }

    /**
     *
     * @param currentPlayer - sets the current player.
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     *
     * @return - gets the current player.
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     *
     * @return - gets the current deck.
     */
    public Deck getDeck() {
        return deck;
    }

    /**
     *
     * @return - returns all of the players.
     */
    public ArrayList<Player> getPlayers() {
        return players;
    }

    /**
     * This gets the player who starts the game. The first player to draw.
     */
    public void start() {
        currentPlayer = players.get(1);
    }

    /**
     * Changes the current player to the nextPlayer in line.
     */
    public void nextPlayer() {
        int pos = findCurrent();
        if (pos == players.size() - 1) {
            currentPlayer = players.get(0);
        } else {
            currentPlayer = players.get(pos + 1);
        }
    }

    /**
     *
     * @return - Finds the position of the current player in the player array.
     */
    public int findCurrent() {
        for (int i = 0; i < players.size(); i++) {
            if (currentPlayer.equals(players.get(i))) {
                return i;
            }
        }
        return 0;
    }


}
