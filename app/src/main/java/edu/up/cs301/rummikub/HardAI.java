package edu.up.cs301.rummikub;

import java.util.Random;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.infoMsg.GameInfo;

/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 4/23/2022
 *
 * Contains playing style for hard ai.
 *
 * Bugs: AI not fully finished.
 *
 * */

public class HardAI extends GameComputerPlayer  {

    public HardAI(String name) {
        super(name);
    }

    @Override
    protected void receiveInfo(GameInfo info) {
        RummiGameState received_State = new RummiGameState((RummiGameState) info);
        int turnChoice = (int) (Math.random() * 2);

        if(playerNum != received_State.getPlayerId()){
            return;
        }

        if (playerNum == received_State.getPlayerId()){
            //Chooses between two options
            //1. Drawing (happens if turn choice is 0 or if there are no tiles in the ai's hand
            if ((turnChoice == 0 && received_State.getDeck() != null) || (turnChoice == 1 && received_State.getPlayerHand() == null)){
                DrawTile draw = new DrawTile(this);
                game.sendAction(draw);
            }
            //2. Placing tile (happens if turn choice is 1 or if there are no tiles to draw
            if ((turnChoice == 1 && received_State.getPlayerHand() != null) || (turnChoice == 0 && received_State.getDeck() == null)){
                PlaceTile place = new PlaceTile(this);
                game.sendAction(place);
            }
        }
    }
}
