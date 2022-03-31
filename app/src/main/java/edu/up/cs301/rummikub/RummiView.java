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

    private static final int tileColor        = Color.parseColor("#FAD3BB");
    private static final int tileColor_invis  = Color.parseColor("#FAD3BB");
    private static final int tileColor_black  = Color.parseColor("#000000");
    private static final int tileColor_blue   = Color.parseColor("#29ABE2");
    private static final int tileColor_red    = Color.parseColor("#ED1C24");
    private static final int tileColor_orange = Color.parseColor("#FBB03B");
    private static final int gridColor        = Color.parseColor("#FFFFFF");
    private static final int trayColor        = Color.parseColor("#AE9276");

    Paint tilePaint         = new Paint();
    Paint tilePaint_invis   = new Paint();
    Paint tilePaint_black   = new Paint();
    Paint tilePaint_blue    = new Paint();
    Paint tilePaint_red     = new Paint();
    Paint tilePaint_orange  = new Paint();
    Paint gridPaint         = new Paint();
    Paint trayPaint         = new Paint();

    private final int rowCount      = 4;  //# of rows in grid
    private final int colCount      = 13; //# of cols in grid

    public RummiView(Context context, AttributeSet attrs) {
        super(context, attrs);

        info = new Tile();
        setWillNotDraw(false);

        // initialize colors
        tilePaint.setColor(tileColor);
        tilePaint.setStyle(Paint.Style.FILL);

        // to turn tile color invisible, set style to STROKE instead of FILL
        tilePaint_invis.setColor(tileColor_invis);
        tilePaint.setStyle(Paint.Style.STROKE);

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

        trayPaint.setColor(trayColor);
        trayPaint.setStyle(Paint.Style.FILL);

    }

    @Override
    public void onDraw(Canvas c) {
        //Need to add: Way to get info for drawings (location, height, width)
        createGrid(c);
        createTile(c);
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

        for (int i = 0; i <= colCount; i++)
        {
            float pos = (width_grid / colCount) * (i);
            c.drawLine(pos, 0, pos, height_grid, gridPaint);
        }

        // horizontal lines

        for (int i = 0; i <= rowCount; i++)
        {
            float pos = (height_grid / rowCount) * (i);
            c.drawLine(0, pos, width_grid, pos, gridPaint);
        }

    }

    /* in charge of drawing the player's hand tray for the .xml */
    public void createTray(Canvas c) {

        float height_tray = (((float) getHeight() / 3) * 2) + 40;
        float width_tray = getWidth() - 40;
        c.drawRect(40, height_tray, width_tray, height_tray + 225, trayPaint);

    }

    public RummikubGameState getGameState() { return newState; }
}