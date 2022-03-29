package edu.up.cs301.rummikub;


import java.util.ArrayList;

/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 3/17/2022
 *
 * Manages the tiles the AI can play.
 *
 * Bugs:
 *
 * */

public class AIHand {
    //I don't know how we plan to deal with player ID's for AI's so delete if we don't need it
    private int p_id;
    private ArrayList<Tile> hand;

    //constructor for AIHand
    public AIHand(int init_id) {
        this.p_id = init_id;
        hand = new ArrayList<Tile>();
    }

    //Deep copy constructor for AIHand
    public AIHand(AIHand orig) {
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
