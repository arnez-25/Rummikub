package edu.up.cs301.rummikub;

import static org.junit.Assert.*;

import edu.up.cs301.game.R;

public class RummikubGameStateTest {

    @org.junit.Test
    public void testToString() {
    }

    @org.junit.Test
    public void changeTurn() {
        RummikubGameState test = new RummikubGameState();
        test.toString();
        test.changeTurn();
        assertEquals(1, test.getPlayerId());

    }

    @org.junit.Test
    public void drawTile_action() {
        RummikubGameState test = new RummikubGameState();
        test.drawTile_action(test.getDeck());
        assertNotEquals(test.getPlayerHand().get(1).size(),test.getPlayerHand().get(0).size() );

    }


}