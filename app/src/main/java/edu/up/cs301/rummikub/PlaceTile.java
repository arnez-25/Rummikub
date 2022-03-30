package edu.up.cs301.rummikub;

import edu.up.cs301.game.LocalGame;

/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 3/23/2022
 *
 * Handles a player placing a tile.
 *
 * Bugs:
 *
 * */

public class PlaceTile {

    /**
     * Checks to do:
     * 1) If a player has not met the win condition yet, continue to Step (2).
     * 2) If the run/series that the player is playing towards has at least 3 tiles, continue to Step (2).
     * 3) Step (3) is based on whether the played collection of tiles is a run or a series
     *      Run: If the following criteria are met for the played tile, then the move is legal:
     *           - Has the same color (Black, Blue, Orange, Red) as its neighbors
     *           - Is +1 larger than the tile proceeding it
     *           - Is -1 smaller than the tile after it
     *           - All of above, OR is a Joker Tile
     *      Series: If the following criteria are met for the played tile, then the move is legal:
     *           - Has a different color (Black, Blue, Orange, Red) than its neighbors
     *           - Is the same numerical value as its neighbors
     *           - All of above, OR is a Joker Tile
     * 4) If a player has not already “broken out”, they must do so with their own run/series of at least 3
     *    of their own tiles. Additionally, their played run/series must have a combined numerical score of 30
     *    or greater, and NOT include a Joker of any kind.
     */

    /**
     * public boolean isIllegal(Tile placed_tile) {
     *
     *         if (RummikubLocalGame.checkIfGameOver() != null) return true;
     *
     * }
    */
}
