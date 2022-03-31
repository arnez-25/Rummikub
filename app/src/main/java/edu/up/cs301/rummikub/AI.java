package edu.up.cs301.rummikub;


import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.pig.PigGameState;
import edu.up.cs301.pig.PigRollAction;

/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 3/30/2022
 *
 * Compiles AIs
 *
 * Bugs:
 *
 * */

public class AI extends GameComputerPlayer {

    public AI(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        RummikubGameState recieved_State = new RummikubGameState ((RummikubGameState) info);

        if(playerNum != recieved_State.getCurr_turn()){
            return;
        }
        if (playerNum == recieved_State.getCurr_turn()){
            DrawTile draw = new DrawTile(this);
            game.sendAction(draw);
        }
    }
    //Needs to be able to tell which AI is which
    //Updates game and makes decisions (vary between AI)

}
