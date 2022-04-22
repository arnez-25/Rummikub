package edu.up.cs301.rummikub;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;

public class RummiLocalGame extends LocalGame {
    RummiGameState official_ref;

    /**
     * This ctor creates a new game state
     */
    public RummiLocalGame() {
        //TODO  You will implement this constructor
        official_ref = new RummiGameState();
    }

    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method

        RummiGameState current_Copy = new RummiGameState(official_ref);
        //Method sends copy to player
        p.sendInfo(current_Copy);
    }

    @Override
    protected boolean canMove(int playerIdx) {
        return true;
    }

    @Override
    protected String checkIfGameOver() {
        //Basic check to see either players have emptied their hands
        if(official_ref.getPlayerHand().get(0).isEmpty()){
            return "Player 1 has won the game!";
        }
        else if (official_ref.getPlayerHand().get(1).isEmpty()){
            return "Player 2 has won the game!";
        }
        // Additional check may be required if the time limit has been reached
        else return null;
    }

    @Override
    protected boolean makeMove(GameAction action) {
        if (action instanceof DrawTile){
            if (official_ref.getPlayerId() == 0) {
                //official_ref.getPlayer().get(0).add(official_ref.getDeck().get(0));

            }
        }
        return true;
    }
}
