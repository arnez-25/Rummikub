package edu.up.cs301.rummikub;


import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 3/17/2022
 *
 * Compiles AIs
 *
 * Bugs:
 *
 * */

public class AI extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public AI(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {

    }
    //Needs to be able to tell which AI is which
    //Updates game and makes decisions (vary between AI)

}
