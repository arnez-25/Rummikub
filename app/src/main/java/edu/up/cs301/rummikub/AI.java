package edu.up.cs301.rummikub;


import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 3/30/2022
 *
 * Compiles AIs
 *
 * Bugs: AI not fully finished.
 *
 * */

public class AI extends GameComputerPlayer {

    public AI(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        RummiGameState recieved_State = new RummiGameState((RummiGameState) info);

        if(playerNum != recieved_State.getPlayerId()){
            return;
        }
        /*
        if (playerNum == recieved_State.getPlayerId()){
            //if there are tiles in the pile it will continue to draw
            //else it will place tiles
            if (){
                DrawTile draw = new DrawTile(this);
                game.sendAction(draw);
            }
            else{
                PlaceTile place = new PlaceTile(this);
                game.sendAction(place);
            }
        }

        */
    }
    //Needs to be able to tell which AI is which
    //Updates game and makes decisions (vary between AI)

}
