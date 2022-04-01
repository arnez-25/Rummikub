package edu.up.cs301.rummikub;

import android.graphics.Color;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

import edu.up.cs301.game.infoMsg.GameState;

/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 3/17/2022
 *
 * (description here)
 *
 * Bugs:
 *
 * */

public class RummikubGameState extends GameState {

    /**
     * Instance Variables REQ. for Rummikub:
     *  - player object
     *      - name of player
     *      - # of tiles in hand
     *      - current score
     *      - hasWon boolean(?)
     *  - # of tiles in deck/pile
     *  - # of tiles on board
     *  - current turn
     *  - timer
     */

    private int playerId;
    private int timer;

    //These are the player hands
    private ArrayList<Tile> player1_hand = new ArrayList<>();
    private ArrayList<Tile> player2_hand = new ArrayList<>();

    //2-D Array for both player hands and player tile
    private ArrayList<ArrayList<Tile>> player_hand = new ArrayList<>();

    //This is the list of tiles currently on the board
    private ArrayList<Tile> board = new ArrayList<Tile>();

    //This is the pile of tiles the players are gonna draw from
    private ArrayList<Tile> deck = new ArrayList<Tile>();


    public RummikubGameState() {
        playerId = 0;
        timer = 100;
        //Setting up the begginning of the game the default constructor should only be called once
        setup(player_hand, deck);

    }
    /*
          External Citation
          Date: 31 March 2022
          Problem: Creating deep copy of ArrayList
          Resource:
          https://codippa.com/deep-copy-arraylist-java/
          Solution: I needed to implement Cloneable interface to Tile then iterate over the List and clone them into the copy
          I don't understand why the try-catch is necessary, but no red squiggly make me happy
         */

    //Copy Constructor I don't know if it's deep copy since the ArrayList aren't fully instantiated
    public RummikubGameState(RummikubGameState copy){
        this.playerId = copy.getPlayerId();
        this.timer = copy.getTimer();
        //Setting the 2D array for the copy and making them a deep copy
        this.player_hand.add(new ArrayList<Tile>());
        for(int i = 0; i < copy.getPlayerHand().size(); i++){                       //This section could probably be turned into a helper method
            try {
                this.player_hand.get(0).add((Tile) copy.getPlayerHand().get(0).get(i).clone());         // I don't understand why the try-catch is necessary
            }catch  (CloneNotSupportedException e3) {
                e3.printStackTrace();
            }
        }
        this.player_hand.add(new ArrayList<Tile>());
        for(int j = 0; j < copy.getPlayerHand().size(); j++){
            try {
                this.player_hand.get(1).add((Tile) copy.getPlayerHand().get(1).get(j).clone());
            }catch  (CloneNotSupportedException e3) {
                e3.printStackTrace();
            }
        }

        for(int f = 0; f < copy.getDeck().size(); f++){
            try{
                this.deck.add((Tile) copy.getDeck().get(f).clone());

            }catch  (CloneNotSupportedException e3) {
                e3.printStackTrace();
            }
        }
    }

    @Override
    public String toString() {

        String str_player1_hand = "", str_player2_hand = "", str_board = "", str_deck = "";

        for (int i = 0; i < player1_hand.size(); i++) {
            str_player1_hand += player1_hand.get(i) + "\n";
        }

        for (int i = 0; i < player2_hand.size(); i++) {
            str_player2_hand += player2_hand.get(i) + "\n";
        }

        for (int i = 0; i < board.size(); i++) {
            str_board += board.get(i) + "\n";
        }

        for (int i = 0; i < deck.size(); i++) {
            str_deck += deck.get(i) + "\n";
        }

        String str_return

                = "~~ Current Game Info ~~                      \n\n"
                + "Currently Player " + playerId + "'s Turn    \n"
                + "Timer: " + timer + "s                        \n\n"
                + "Player 1 Hand:                               \n"
                + player_hand.get(0).toString() + "                          \n\n"
                + "Player 2 Hand:                               \n"
                + player_hand.get(1).toString() + "                          \n\n"
                + "Tiles on Board:                              \n"
                + board.toString() + "                               \n\n"
                + "Tiles in Pile:                               \n"
                + deck.toString();

        return str_return;

    }
    // helper method to transfer initial tiles in each players deck
    private void transfer_tile( ArrayList<Tile> hand,  ArrayList<Tile> deck){
        for(int i = 0; i < 7;i++){
            hand.add(deck.get(0));
            deck.remove(0);     // The ArrayList remove method shifts all elements to the left
            // In theory I can keep the index at 0 since removing from deck will just shift the object to the let
        }
    }

    // Helper method to change the turn of the player
    public void changeTurn(){
        if (playerId == 0) playerId = 1;
        else if (playerId == 1) playerId = 0;
    }

    //Helper method for adding tiles to player hand(s)
    private void drawTile(ArrayList<Tile> player_hand, ArrayList<Tile> deck) {
        player_hand.add(deck.get(0));
        deck.remove(0);
    }

    //This action should draw tile for the current player. This tile will be taken from the tile pile and added to the current players hand
    public boolean drawTile_action(ArrayList<Tile> deck){
        if (playerId == 0){
            //Add tile from tile pile to player_1's hand
            drawTile(player_hand.get(0), deck);
            changeTurn();
            return true;

        } else if(playerId == 1){
            //Add tile from tile pile to player_2's hand
            drawTile(player_hand.get(1), deck);
            changeTurn();
            return true;

        }

        return false;

    }

    //Helper function that adds all the tiles in the game (except for jokers) to an arrayList
    private void setup_pile(ArrayList<Tile> deck){
        for(int i = 1; i <= 2; i++){
            //This should create tiles from 1 - 12 for each color
            for(int j = 1; j <= 13; j++){
                deck.add(new Tile(1, j));
                deck.add(new Tile(2, j));
                deck.add(new Tile(3, j));
                deck.add(new Tile(4, j));

            }
        }
    }

    //Helper method for adding tiles to player hand(s) at the start of the game
    private void mulligan(ArrayList<Tile> deck) {
        for(int i = 0; i < 7; i++) {
            drawTile(player1_hand, deck);
            drawTile(player2_hand, deck);
        }
    }

    //Helper method to shuffle the tile pile once it's instantiated
    private void shuffle(ArrayList<Tile> deck){
        Collections.shuffle(deck);
    }

    //Helper function that sets up the 2D list
    private void setup(ArrayList<ArrayList<Tile>> list, ArrayList<Tile> deck){
        //First two are the player hands
        list.add(new ArrayList<Tile>());
        list.add(new ArrayList<Tile>());
        //This is the tile pile
        setup_pile(deck); //Instantiating all the tiles in the deck
        shuffle(deck);
        mulligan(deck);
        transfer_tile(list.get(0), deck);
        transfer_tile(list.get(1), deck);
    }

    //Checks if either player hand is empty. This should be called at the end of each turn
    public boolean isWin(){
        if(player1_hand.isEmpty()){
            //The print line is just a placeholder, the method should change the screen to reflect who's won the game
            System.out.println("Player 1 has won the game!");
            return true;
        }
        if(player2_hand.isEmpty()){
            System.out.println("Player 2 has won the game");
        }
        //If neither player hand is empty then the game continues
        return false;
    }


    //Getters and Setters
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public int getTimer() {
        return timer;
    }

    public void setTimer(int timer) {
        this.timer = timer;
    }

    public ArrayList<Tile> getPlayer1_hand() {
        return player1_hand;
    }

    public void setPlayer1_hand(ArrayList<Tile> player1_hand) {
        this.player1_hand = player1_hand;
    }

    public ArrayList<Tile> getPlayer2_hand() {
        return player2_hand;
    }

    public void setPlayer2_hand(ArrayList<Tile> player2_hand) {
        this.player2_hand = player2_hand;
    }

    public ArrayList<Tile> getBoard() {
        return board;
    }

    public void setBoard(ArrayList<Tile> board) {
        this.board = board;
    }

    public ArrayList<Tile> getDeck() {
        return deck;
    }

    public void setDeck(ArrayList<Tile> deck) {
        this.deck = deck;
    }

    public ArrayList<ArrayList<Tile>> getPlayerHand(){ return player_hand;}



}
