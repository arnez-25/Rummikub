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

public class PaintTile extends SurfaceView {
    //setting up paints and DrawTileData reference
    public Paint tilePaint = new Paint();
    public int tileColor =  0Xfad3bb;
    public Paint numberPaint = new Paint();
    public Tile info;

    public PaintTile(Context context, AttributeSet attrs) {
        super(context, attrs);

        info = new Tile();

        setWillNotDraw(false);

        //initialize colors
        //NOTE TO EVERYONE: These colors are placeholders, please change later.
        tilePaint.setColor(tileColor);
        tilePaint.setStyle(Paint.Style.FILL);
        numberPaint.setColor(Color.GREEN);
        numberPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    public void onDraw(Canvas c) {
        //Need to add: Way to get info for drawings (location, height, width)

        createTile(c);
        //addNum(c);
    }

    public void createTile(Canvas c){
        //Draws the tile (temp values change later)
        c.drawRect(100, 100, 100, 100, tilePaint);
        c.drawText("12", 50, 50, numberPaint);
    }

    public void addNum(Canvas c){
        //Draws number to be added to tile
    }
}