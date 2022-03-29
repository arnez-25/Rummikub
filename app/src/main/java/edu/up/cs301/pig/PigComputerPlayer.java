package edu.up.cs301.pig;

import edu.up.cs301.game.GameComputerPlayer;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameInfo;
import edu.up.cs301.game.util.Tickable;
import java.util.Random;

/**
 * An AI for Pig
 *
 * @author Andrew M. Nuxoll
 * @version August 2015
 */
public class PigComputerPlayer extends GameComputerPlayer {

    /**
     * ctor does nothing extra
     */
    public PigComputerPlayer(String name) {
        super(name);
    }

    /**
     * callback method--game's state has changed
     *
     * @param info
     * 		the information (presumably containing the game's state)
     */
    @Override
    protected void receiveInfo(GameInfo info) {
        // TODO  You will implement this method
        Random random = new Random();
        PigGameState recieved_State = new PigGameState ((PigGameState) info);
        if(playerNum != recieved_State.getId()){
            return;
        }
        if(playerNum == recieved_State.getId()){
            if(random.nextBoolean()){
                PigRollAction roll = new PigRollAction(this);
                game.sendAction(roll);
            }
            else{
                PigHoldAction hold = new PigHoldAction(this);
                game.sendAction(hold);
            }
        }
    }//receiveInfo

}
