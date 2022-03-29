package edu.up.cs301.rummikub;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.pig.PigGameState;

public class RummikubLocalGame extends LocalGame {
    RummikubGameState official_ref;

    /**
     * This ctor creates a new game state
     */
    public RummikubLocalGame() {
        //TODO  You will implement this constructor
        official_ref = new RummikubGameState();
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method

        RummikubGameState current_Copy = new RummikubGameState(official_ref);
        //Method sends copy to player
        p.sendInfo(current_Copy);
    }

    @Override
    protected boolean canMove(int playerIdx) {
        return true;
    }

    @Override
    protected String checkIfGameOver() {
        return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        if(action instanceof DrawTile){
            return true;
        }
        return true;
    }
}
