package edu.up.cs301.rummikub;

import java.util.ArrayList;
import java.util.List;

// I'll comment this tomorrow, I'm tired
final class TileCheck {

    private static final int MIN_GROUP = 3;
    private static final int MAX_GROUP = 13;

    private ArrayList<Tile> tiles;

    public TileCheck() {
        tiles = new ArrayList<>();
    }

    public boolean isLegalSet(final ArrayList<Tile> tiles) {

        final List<Integer> colors = new ArrayList<Integer>();

        /* trying out a for-each loop */
        for(final Tile tile : tiles) {
            final int curr_tileColor = tile.getColor();
            if (0 == curr_tileColor) {
                continue;
            }
            
            if (tile.getColor() == curr_tileColor) {
                return false;
            }

            colors.add(curr_tileColor);
        }
        return true;
    }

    public boolean isLegalRun(final ArrayList<Tile> tiles) {

        int curr_tileNum = 0;
        boolean isFirst = true;

        for (int i = 0; i < tiles.size(); i++) {
            final Tile tile = tiles.get(i);
            final int tileNum = tile.getTileNum();

            if (tileNum != 0 && isFirst)  {
                curr_tileNum = tileNum;
                isFirst = false;
            }

            if (tileNum != 0 && curr_tileNum != tileNum) return false;

            if (!isLegalSet(tiles)) return false;

            if (curr_tileNum < MAX_GROUP) curr_tileNum = curr_tileNum + 1;
        }

        return true;

    }

    private boolean isLegalCurrGroup() {
        return (tiles.size() <= MAX_GROUP)
                && (tiles.size() >= MIN_GROUP)
                && isLegalSet(tiles)
                && isLegalRun(tiles);
    }

}
