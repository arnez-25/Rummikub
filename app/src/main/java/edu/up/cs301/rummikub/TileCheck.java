package edu.up.cs301.rummikub;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Color;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.rummikub.Tile;

import static java.util.Collections.unmodifiableList;

final class TileCheck {

    private static final int MIN_GROUP = 3;
    private static final int MAX_GROUP = 13;

    private Tile[] tiles = new Tile[];

    private int

    public TileCheck(final Tile[] tiles) {
    }

    public boolean isValidSet(final Tile[] tiles) {
        final Color[] colors = new Color[];

        for(final Tile tile : tiles) {
            Color curr_color = tile.getColor();
        }
    }

    public boolean isValidRun(final Tile[] tiles) {

    }




}
