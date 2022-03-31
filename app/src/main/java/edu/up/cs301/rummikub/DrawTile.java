package edu.up.cs301.rummikub;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.actionMsg.GameAction;


/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 3/30/2022
 *
 *
 * Bugs:
 *
 * */

public class DrawTile extends GameAction{

    /**
     * constructor for GameAction
     *
     * @param player the player who created the action
     */
    public DrawTile(GamePlayer player) {
        super(player);
    }

}
