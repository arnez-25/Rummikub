package edu.up.cs301.rummikub;

import edu.up.cs301.game.GamePlayer;

/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 3/23/2022
 *
 * Handles all game actions like DrawTile, EndTurn, and PlaceTile.
 *
 * Bugs:
 *
 * */

public abstract class GameAction {
    //Code from GameAction from PigGame
    private GamePlayer player;

    public GameAction(GamePlayer player) { this.player = player; }

    public GamePlayer getPlayer() {
        return player;
    }

    public void setPlayer(GamePlayer p) {
        this.player = p;
    }
}
