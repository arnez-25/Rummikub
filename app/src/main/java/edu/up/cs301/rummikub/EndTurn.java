package edu.up.cs301.rummikub;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;

/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 3/23/2022
 *
 * Handles player ending turn action.
 *
 * Bugs:
 *
 * */

public class EndTurn extends GameAction{

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public EndTurn(GamePlayer player) {
        super(player);
    }
}
