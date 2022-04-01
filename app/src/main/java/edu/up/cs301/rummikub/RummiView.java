package edu.up.cs301.rummikub;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 3/30/2022
 *
 * This class is designed to draw our Rummikub tiles.
 *
 * Bugs:
 *
 * */

/* probably the single greatest name for a class of all time */
public class RummiView extends SurfaceView {
    //setting up paints and DrawTileData reference

    public RummikubGameState newState;
    public Tile info;

    private static final int tileColor          = Color.parseColor("#FAD3BB");
    private static final int boardColor_invis   = Color.parseColor("#2C3493");
    private static final int trayColor_invis    = Color.parseColor("#AE9276");
    private static final int gridColor          = Color.parseColor("#FFFFFF");
    private static final int tileColor_black    = Color.parseColor("#000000");
    private static final int tileColor_blue     = Color.parseColor("#29ABE2");
    private static final int tileColor_red      = Color.parseColor("#ED1C24");
    private static final int tileColor_orange   = Color.parseColor("#FBB03B");

    Paint tilePaint             = new Paint();
    Paint boardPaint_invis      = new Paint();
    Paint trayPaint_invis       = new Paint();
    Paint gridPaint             = new Paint();
    Paint tilePaint_black       = new Paint();
    Paint tilePaint_blue        = new Paint();
    Paint tilePaint_red         = new Paint();
    Paint tilePaint_orange      = new Paint();

    private float height_tile;
    private float width_tile;

    public RummiView(Context context, AttributeSet attrs) {
        super(context, attrs);

        info = new Tile();
        setWillNotDraw(false);

        // initialize colors
        tilePaint.setColor(tileColor);
        tilePaint.setStyle(Paint.Style.FILL);

        // to turn tile color invisible, the color value will be the same as the background B)
        boardPaint_invis.setColor(boardColor_invis);
        boardPaint_invis.setStyle(Paint.Style.FILL);

        trayPaint_invis.setColor(trayColor_invis);
        trayPaint_invis.setStyle(Paint.Style.FILL);

        gridPaint.setColor(gridColor);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(3);

        tilePaint_black.setColor(tileColor_black);
        tilePaint_black.setStyle(Paint.Style.FILL);

        tilePaint_blue.setColor(tileColor_blue);
        tilePaint_blue.setStyle(Paint.Style.FILL);

        tilePaint_red.setColor(tileColor_red);
        tilePaint_red.setStyle(Paint.Style.FILL);

        tilePaint_orange.setColor(tileColor_orange);
        tilePaint_orange.setStyle(Paint.Style.FILL);

        //initializing the tiles
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 13; j++){
                info.setColor(j);
                info.setTileNum(i);
            }
        }
    }

    @Override
    public void onDraw(Canvas c) {
        //Need to add: Way to get info for drawings (location, height, width)
        createGrid(c);
        //createTile(c);
        createTray(c);
    }

    public void createTile(Canvas c){
        //Draws the tile (temp values change later)
        c.drawRect(5, 5, 115, 217, tilePaint);
        c.drawText("" + info.getTileNum(), 10, 20, tilePaint_black);
    }

    public void createGrid(Canvas c){

        /*
          External Citation
          Date: 27 March 2022
          Problem: Drawing a grid on RummiView
          Resource:
          https://stackoverflow.com/questions/36600594/android-drawing-grid-in-background
          Solution: Apparently all I needed to do was use the drawLine feature that's part of the Canvas import
         */

        float height_grid = ((float) getHeight() / 3) * 2;
        float width_grid = getWidth();

        // vertical lines

        int colCount = 13; //# of cols in grid
        for (int i = 0; i <= colCount; i++)
        {
            float pos = (width_grid / colCount) * (i);
            c.drawLine(pos, 0, pos, height_grid, gridPaint);
        }

        // horizontal lines

        int rowCount = 4; //# of rows in grid

        for (int i = 0; i <= rowCount; i++)
        {
            float pos = (height_grid / rowCount) * (i);
            c.drawLine(0, pos, width_grid, pos, gridPaint);
        }

        /**
         *   Instead of drawing new tiles on the surface view, we instead
         *   draw invisible tiles within each of the grid allotments.
         *   When a player wants to play a tile from their hand onto the
         *   board, we use OnTouch() hitboxes to determine which invisible
         *   tile was selected. Then, we set the Paint from dark blue to
         *   tan (thus making it visible), and reference the tile number
         *   and color from hand to create a matching drawText.
         *      - Chase
         */

        // invis tile placement

        float pos_x1, pos_y1, pos_x2, pos_y2;

        for (int i = 0; i < colCount; i++) {

            for (int j = 0; j < rowCount; j++) {

                pos_x1 = (width_grid  / colCount) * (i);
                pos_y1 = (height_grid / rowCount) * (j);
                pos_x2 = (width_grid  / colCount) * (i + 1);
                pos_y2 = (height_grid / rowCount) * (j + 1);
                //System.out.println(pos_x2);
                c.drawRect(pos_x1 + 10, pos_y1 + 10, pos_x2 - 10, pos_y2 - 10, boardPaint_invis);

                if ((i == 0) && (j == 0)) {
                    setWidth_tile(pos_x2);
                    setHeight_tile(pos_y2);
                }

            }
        }
    }

    /* in charge of drawing the player's hand tray for the .xml */
    public void createTray(Canvas c) {

        float height_tray = (((float) getHeight() / 3) * 2) + 20;
        float width_tray = getWidth() - 20;

        c.drawRect(20, height_tray, width_tray, height_tray + height_tray, trayPaint_invis);

        /**
         * Here we're essentially doing what we did earlier within the createGrid method.
         * This nested for-loop is making a 2x12 array of invisible "tile" drawings to be
         * colored in earlier with onTouch() methods (represents the hand)
         */

        int colCount = 12;
        int rowCount = 2;

        float pos_x1, pos_y1, pos_x2, pos_y2;

        for (int i = 0; i < colCount; i++) {

            for (int j = 0; j < rowCount; j++) {

                pos_x1 = (getWidth_tile() * (i))        + 60;
                pos_y1 = (getHeight_tile() * (j))       + height_tray + 10;
                pos_x2 = (getWidth_tile() * (i + 1))    + 60;
                pos_y2 = (getHeight_tile() * (j + 1))   + height_tray + 10;

                c.drawRect(pos_x1 + 10, pos_y1 + 10, pos_x2 - 10, pos_y2 - 10, trayPaint_invis);
            }
        }
    }

    public float getHeight_tile() {
        return height_tile;
    }

    public void setHeight_tile(float height_tile) {
        this.height_tile = height_tile;
    }

    public float getWidth_tile() {
        return width_tile;
    }

    public void setWidth_tile(float width_tile) {
        this.width_tile = width_tile;
    }

    public RummikubGameState getGameState() { return newState; }
}