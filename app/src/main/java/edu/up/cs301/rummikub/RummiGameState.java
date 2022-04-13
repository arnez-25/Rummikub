package edu.up.cs301.rummikub;

import java.util.ArrayList;
import java.util.Collections;

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

public class RummiGameState extends GameState {

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
    private ArrayList<ArrayList<Tile>> player_hand = new ArrayList<>();     //2-D Array for both player hands and player tile
    private ArrayList<Tile> board = new ArrayList<Tile>();  //This is the list of tiles currently on the board
    private ArrayList<Tile> deck = new ArrayList<Tile>();   //This is the pile of tiles the players are gonna draw from


    /**
     * Initial Constructor
     *
     * Should only be called once at the beginning of the game
     */
    public RummiGameState() {

        playerId = 0;
        timer = 100;
        //Setting up the beginning of the game the default constructor should only be called once
        setup(player_hand, deck);

    }


    /**
     * Master Copy constructor
     *
     * This is not meant for the players
     *
     * @param copy
     */
    public RummiGameState(RummiGameState copy){

        this.playerId = copy.getPlayerId();
        this.timer = copy.getTimer();
        //Setting the 2D array for the copy and making them a deep copy
        this.player_hand.add(new ArrayList<Tile>());
        deep_copy(this.player_hand.get(0), copy.getPlayerHand().get(0));
        this.player_hand.add(new ArrayList<Tile>());
        deep_copy(this.player_hand.get(1), copy.getPlayerHand().get(1));
        deep_copy(this.deck, copy.getDeck());
    }

    /**
     * Copy constructor
     *
     * meant for players
     *
     * @param copy - The Gamestate being copied
     * @param PlayerId - an int meant to help nullify the deck the player shouldnt see
     */
    public RummiGameState(RummiGameState copy, int PlayerId){
        this.playerId = copy.getPlayerId();
        this.timer = copy.getTimer();
        //Setting the 2D array for the copy and making them a deep copy
        this.player_hand.add(new ArrayList<Tile>());
        deep_copy(this.player_hand.get(0), copy.getPlayerHand().get(0));
        this.player_hand.add(new ArrayList<Tile>());
        deep_copy(this.player_hand.get(1), copy.getPlayerHand().get(1));
        deep_copy(this.deck, copy.getDeck());

        //replace the tiles in the other player's hand with blanks to hide them
        ArrayList<Tile> secret = player_hand.get(1-playerId);
        int numTiles = secret.size();
        secret.clear();
        for(int i = 0; i < numTiles; ++i) {
            secret.add(Tile.BLANK_TILE);
        }
        // The clear method should just erase all the objects in the list and set it to null


    }

    @Override
    public String toString() {

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
    //WARNING: this will only add too player 0s hand
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
            drawTile(player_hand.get(0), deck);
            drawTile(player_hand.get(1), deck);
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
        if(player_hand.get(0).isEmpty()){
            //The print line is just a placeholder, the method should change the screen to reflect who's won the game
            System.out.println("Player 1 has won the game!");
            return true;
        }
        if(player_hand.get(1).isEmpty()){
            System.out.println("Player 2 has won the game");
        }
        //If neither player hand is empty then the game continues
        return false;
    }

    /**
     * Helper Method for making a deep copy for the copy constructor
     *
     * Unsure weather it works or not but it compiles ¯\_(ツ)_/¯
     */
    public void deep_copy(ArrayList<Tile> copy, ArrayList<Tile> reference){
        copy.clear();
        for(Tile t : reference){                       //This section could probably be turned into a helper method
            copy.add(new Tile(t));         // I don't understand why the try-catch is necessary
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
