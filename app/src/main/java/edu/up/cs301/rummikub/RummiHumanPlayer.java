package edu.up.cs301.rummikub;

import android.os.Bundle;
import android.view.View;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;


/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 3/23/2022
 *
 * (insert description)
 *
 * Bugs:
 *
 * */

public class RummiHumanPlayer extends GameHumanPlayer {


    /**
     * constructor does nothing extra
     */
    public RummiHumanPlayer(String name) {
        super(name);
    }

    @Override
    public View getTopView() {
        return null;
    }

    @Override
    public void receiveInfo(GameInfo info) {

    }

    @Override
    public void setAsGui(GameMainActivity activity) {
        activity.setContentView(R.layout.rummikub_layout);
    }
}
