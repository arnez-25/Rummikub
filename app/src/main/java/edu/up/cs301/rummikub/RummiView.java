package edu.up.cs301.rummikub;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.SurfaceView;

/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 3/17/2022
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

    private static final int tileColor        = Color.parseColor("#FAD3BB");
    private static final int tileColor_black  = Color.parseColor("#000000");
    private static final int tileColor_blue   = Color.parseColor("#29ABE2");
    private static final int tileColor_red    = Color.parseColor("#ED1C24");
    private static final int tileColor_orange = Color.parseColor("#FBB03B");
    private static final int gridColor        = Color.parseColor("#FFFFFF");

    Paint tilePaint         = new Paint();
    Paint tilePaint_black   = new Paint();
    Paint tilePaint_blue    = new Paint();
    Paint tilePaint_red     = new Paint();
    Paint tilePaint_orange  = new Paint();
    Paint gridPaint         = new Paint();

    public RummiView(Context context, AttributeSet attrs) {
        super(context, attrs);

        info = new Tile();
        setWillNotDraw(false);

        //initialize colors
        tilePaint.setColor(tileColor);
        tilePaint.setStyle(Paint.Style.FILL);

        tilePaint_black.setColor(tileColor_black);
        tilePaint.setStyle(Paint.Style.FILL);

        tilePaint_blue.setColor(tileColor_blue);
        tilePaint.setStyle(Paint.Style.FILL);

        tilePaint_red.setColor(tileColor_red);
        tilePaint.setStyle(Paint.Style.FILL);

        tilePaint_orange.setColor(tileColor_orange);
        tilePaint.setStyle(Paint.Style.FILL);

        gridPaint.setColor(gridColor);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(3);

    }

    @Override
    public void onDraw(Canvas c) {
        //Need to add: Way to get info for drawings (location, height, width)
        createGrid(c);
        createTile(c);
    }

    public void createTile(Canvas c){
        //Draws the tile (temp values change later)
        c.drawRect(5, 5, 115, 217, tilePaint);
        c.drawText("" + info.getTileNum(), 10, 20, tilePaint_black);
    }

    public void createGrid(Canvas c){

        final float height = getHeight();
        final float width = getWidth();

        final int rowCount      = 4;  //# of rows in grid
        final int colCount      = 13; //# of cols in grid

        /*
          External Citation
          Date: 27 March 2022
          Problem: Drawing a grid on RummiView
          Resource:
          https://stackoverflow.com/questions/36600594/android-drawing-grid-in-background
          Solution: Apparently all I needed to do was use the drawLine feature that's part of the Canvas import
         */

        // vertical lines

        for (int i = 0; i <= colCount; i++)
        {
            float pos = (width / colCount) * (i);
            c.drawLine(pos, 0, pos, height, gridPaint);
        }

        // horizontal lines

        for (int i = 0; i <= rowCount; i++)
        {
            float pos = (height / rowCount) * (i);
            c.drawLine(0, pos, width, pos, gridPaint);
        }

    }

    public RummikubGameState getGameState() { return newState; }
}