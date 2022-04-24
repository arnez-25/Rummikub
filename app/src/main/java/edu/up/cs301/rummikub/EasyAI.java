package edu.up.cs301.rummikub;


import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 4/23/2022
 *
 * Contains playing style for easy ai.
 *
 * Bugs:
 *
 * */

public class EasyAI extends GameComputerPlayer {

    public EasyAI(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        RummiGameState received_State = new RummiGameState((RummiGameState) info);

        if(playerNum != received_State.getPlayerId()){
            return;
        }

        if (playerNum == received_State.getPlayerId()){
            //if there are tiles in the pile it will continue to draw
            //else it will place tiles
            if (received_State.getDeck() != null){
                DrawTile draw = new DrawTile(this);
                game.sendAction(draw);
            }
            else{
                PlaceTile place = new PlaceTile(this);
                game.sendAction(place);
            }
        }
    }
}
