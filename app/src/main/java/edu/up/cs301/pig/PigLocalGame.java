package edu.up.cs301.pig;

import edu.up.cs301.game.GamePlayer;
import edu.up.cs301.game.LocalGame;
import edu.up.cs301.game.actionMsg.GameAction;
import edu.up.cs301.game.infoMsg.GameState;

import android.util.Log;

// dummy comment, to see if commit and push work from srvegdahl account

/**
 * class PigLocalGame controls the play of the game
 *
 * @author Andrew M. Nuxoll, modified by Steven R. Vegdahl
 * @version February 2016
 */
public class PigLocalGame extends LocalGame {

    PigGameState official_ref;

    /**
     * This ctor creates a new game state
     */
    public PigLocalGame() {
        //TODO  You will implement this constructor
        official_ref = new PigGameState();
    }

    /**
     * can the player with the given id take an action right now?
     */
    @Override
    protected boolean canMove(int playerIdx) {
        //TODO  You will implement this method
        if(official_ref.getId() == playerIdx){
            return true;
        }
        return false;
    }

    /**
     * This method is called when a new action arrives from a player
     *
     * @return true if the action was taken or false if the action was invalid/illegal.
     */
    @Override
    protected boolean makeMove(GameAction action) {
        //TODO  You will implement this method
        if(action instanceof PigHoldAction){
            if(official_ref.getId() == 0){
                official_ref.setPlayer_0(official_ref.getPlayer_0() + official_ref.getRunning_total());
                official_ref.setRunning_total(0);
                official_ref.setId(1);
                return true;
            }
            if(official_ref.getId() == 1){
                official_ref.setPlayer_1(official_ref.getPlayer_1() + official_ref.getRunning_total());
                official_ref.setRunning_total(0);
                official_ref.setId(0);
                return true;
            }
            return false;
        }
        if(action instanceof PigRollAction){
            official_ref.setDie((int)(Math.random() * (6)));
            if(official_ref.getDie() != 1){
                official_ref.setRunning_total(official_ref.getRunning_total() + official_ref.getDie());
                return true;
            }
            if(official_ref.getDie() == 1){
                official_ref.setRunning_total(0);
                if(players.length > 1){
                    if(official_ref.getId() == 1){
                        official_ref.setId(0);
                    }
                    if(official_ref.getId() == 0){
                        official_ref.setId(1);
                    }
                }
                return true;
            }

        }
        return false;
    }//makeMove

    /**
     * send the updated state to a given player
     */
    @Override
    protected void sendUpdatedStateTo(GamePlayer p) {
        //TODO  You will implement this method
        PigGameState current_Copy = new PigGameState(official_ref);
        //Method sends copy to player
        p.sendInfo(current_Copy);
    }//sendUpdatedSate

    /**
     * Check if the game is over
     *
     * @return
     * 		a message that tells who has won the game, or null if the
     * 		game is not over
     */
    @Override
    protected String checkIfGameOver() {
        //TODO  You will implement this method
        if(official_ref.getPlayer_0() >= 50){
            System.out.println("Player 0 wins with a score of " + official_ref.getPlayer_0());
            String message_0 = "Player 0 wins with a score of " + official_ref.getPlayer_0();
            return message_0;
        }
        if(official_ref.getPlayer_1() >= 50){
            System.out.println("Player 1 wins with a score of " + official_ref.getPlayer_1());
            String message_1 = "Player 1 wins with a score of " + official_ref.getPlayer_1();
            return message_1;
        }
        return null;
    }

}// class PigLocalGame
