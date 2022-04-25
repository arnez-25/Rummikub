package edu.up.cs301.rummikub;

import android.util.Log;

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
    private ArrayList<ArrayList<Tile>> player_hand = new ArrayList<ArrayList<Tile>>();     //2-D Array for both player hands and player tile
    private ArrayList<Tile> board = new ArrayList<Tile>();  //This is the list of tiles currently on the board
    private ArrayList<Tile> deck = new ArrayList<Tile>();   //This is the pile of tiles the players are gonna draw from


    // qprivate RummiView myView;

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
        Log.i("RummiGameStateC", this.toString());
        //shuffle(player_hand.get(0));
        //shuffle(player_hand.get(1));
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
        Log.i("RummiGameStateMCC", this.toString());

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

        Log.i("RummiGameStateCC", this.toString());

    }

    @Override
    public String toString() {

        String str_return

                = "~~ Current Game Info ~~                      \n\n"
                + "Currently Player " + playerId + "'s Turn     \n"
                + "Timer: " + timer + "s                        \n\n"
                + "Player 0 Hand:                               \n"
                + player_hand.get(0).toString() + "             \n\n"
                + "Player 1 Hand:                               \n"
                + player_hand.get(1).toString() + "             \n\n"
                + "Tiles on Board:                              \n"
                + board.toString() + "                          \n\n"
                + "Tiles in Pile:                               \n"
                + deck.toString();

        return str_return;

    }

    // Helper method to change the turn of the player
    public void changeTurn(){
        if (playerId == 0) playerId = 1;
        else if (playerId == 1) playerId = 0;
    }

    /**Draws tile from deck by making a deep copy of it
     *
     * @param current_player
     * @param deck
     */

    private void drawTile(ArrayList<Tile> current_player, ArrayList<Tile> deck) {
        current_player.add(new Tile(deck.get(0)));
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
                deck.add(new Tile(1, j, true));
                deck.add(new Tile(2, j, true));
                deck.add(new Tile(3, j, true));
                deck.add(new Tile(4, j, true));

            }
        }
        shuffle();

    }

    //Helper method for adding tiles to player hand(s) at the start of the game
    private void mulligan(ArrayList<Tile> deck) {
        for(int i = 0; i < 7; i++) {
            drawTile(player_hand.get(0), deck);
            deck.remove(0);
            drawTile(player_hand.get(1), deck);
            deck.remove(0);
        }
    }

    //Helper method to shuffle the tile pile once it's instantiated
    private void shuffle(){ Collections.shuffle(getDeck()); }

    //Helper function that sets up the 2D list
    private void setup(ArrayList<ArrayList<Tile>> list, ArrayList<Tile> deck){
        setup_pile(deck); //Instantiating all the tiles in the deck
        //shuffle();

        //First two are the player hands
        list.add(new ArrayList<Tile>());
        list.add(new ArrayList<Tile>());
        //This is the tile pile

        //shuffle(deck);
        mulligan(deck);
        //transfer_tile(list.get(0), deck);
        //transfer_tile(list.get(1), deck);
        Log.i("RummiGameState", this.toString());
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
     * deep_copy
     * Helper method for making a deep
     *
     * copy is copying reference
     *
     *
     * @param copy
     * @param reference
     */
    public void deep_copy(ArrayList<Tile> copy, ArrayList<Tile> reference){
        copy.clear();
        for(Tile t : reference){                       //This section could probably be turned into a helper method
            copy.add(new Tile(t));         // I don't understand why the try-catch is necessary
        }

    }


    //Getters and Setters
    public int getPlayerId() {
        return playerId;
    }

    public void setPlayerId(int playerId) {
        this.playerId = playerId;
    }

    public ArrayList<ArrayList<Tile>> getPlayer() {
        return player_hand;
    }

    public void setPlayer(ArrayList<ArrayList<Tile>> player) {
        this.player_hand = player;
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
