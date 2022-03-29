package edu.up.cs301.rummikub;

import java.util.ArrayList;

/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 3/17/2022
 *
 * Handles all the data that has to do with a player's hand
 *
 * Bugs: Need to fix constructor and deep copy constructor (3/16/2022)
 *
 * */

public class PlayerHand extends Tile {
    private int p_id;
    private ArrayList<Tile> hand;

    //constructor for PlayerHand
    public PlayerHand(int init_id) {
        this.p_id = init_id;
        hand = new ArrayList<Tile>();
    }

    //Deep copy constructor for PlayerHand
    public PlayerHand(PlayerHand orig) {
        this.p_id     = orig.p_id;
        this.hand     = orig.hand;
    }

    @Override
    public String toString(){

        String str_hand = "";

        for(int i = 0; i < hand.size(); i++) {
            str_hand += hand.get(i) + "\n";
        }

        return str_hand;
    }
}