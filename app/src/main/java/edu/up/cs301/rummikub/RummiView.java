package edu.up.cs301.rummikub;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceView;

import java.util.ArrayList;

/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 4/23/2022
 *
 * This class is designed to draw our Rummikub tiles.
 *
 * Bugs:
 *
 * */

/* probably the single greatest name for a class of all time */
public class RummiView extends SurfaceView {
    //setting up paints and DrawTileData reference

    public RummiGameState newState = new RummiGameState();  // Gamestate only meant to test code
    public RummiHumanPlayer myPlayer;
    public ArrayList<Tile> hand;
    //public Tile info; might wanna get rid of?????

    private static final int tileColor          = Color.parseColor("#F9E2D0");
    private static final int tileColor2         = Color.parseColor("#E2cfb5");
    private static final int boardColor_invis   = Color.parseColor("#2C3493");
    private static final int trayColor_invis    = Color.parseColor("#AE9276");
    private static final int gridColor          = Color.parseColor("#C79C6C");

    private static final int tileColor_black    = Color.parseColor("#000000");
    private static final int tileColor_blue     = Color.parseColor("#29ABE2");
    private static final int tileColor_red      = Color.parseColor("#ED1C24");
    private static final int tileColor_orange   = Color.parseColor("#FBB03B");

    Paint tilePaint             = new Paint();
    Paint tilePaint2            = new Paint();
    Paint boardPaint_invis      = new Paint();
    Paint trayPaint_invis       = new Paint();
    Paint gridPaint             = new Paint();

    Paint tileNumPaint          = new Paint();

    /*Paint tilePaint_black       = new Paint();
    Paint tilePaint_blue        = new Paint();
    Paint tilePaint_red         = new Paint();
    Paint tilePaint_orange      = new Paint();*/

    private float pos_x1, pos_y1, pos_x2, pos_y2;

    private float width_tile, height_tile;
    private float width_grid, height_grid;
    private float width_tray, height_tray;

    public Paint tmp = new Paint();

    public RummiView(Context context, AttributeSet attrs) {
        super(context, attrs);

        setWillNotDraw(false);

        tmp.setColor(tileColor);
        tmp.setStyle(Paint.Style.FILL);

        // initialize colors
        tilePaint.setColor(tileColor);
        tilePaint.setStyle(Paint.Style.FILL);

        tilePaint2.setColor(tileColor2);
        tilePaint2.setStyle(Paint.Style.FILL);

        // to turn tile color invisible, the color value will be the same as the background B)
        boardPaint_invis.setColor(boardColor_invis);
        boardPaint_invis.setStyle(Paint.Style.FILL);

        trayPaint_invis.setColor(trayColor_invis);
        trayPaint_invis.setStyle(Paint.Style.FILL);

        gridPaint.setColor(gridColor);
        gridPaint.setStyle(Paint.Style.STROKE);
        gridPaint.setStrokeWidth(3);

        tileNumPaint.setColor(tileColor_black);
        tileNumPaint.setStyle(Paint.Style.FILL);
        tileNumPaint.setTextSize(40);

        /*tilePaint_black.setColor(tileColor_black);
        tilePaint_black.setStyle(Paint.Style.FILL);
        tilePaint_black.setTextSize(60);

        tilePaint_blue.setColor(tileColor_blue);
        tilePaint_blue.setStyle(Paint.Style.FILL);
        tilePaint_blue.setTextSize(60);

        tilePaint_red.setColor(tileColor_red);
        tilePaint_red.setStyle(Paint.Style.FILL);
        tilePaint_red.setTextSize(60);

        tilePaint_orange.setColor(tileColor_orange);
        tilePaint_orange.setStyle(Paint.Style.FILL);
        tilePaint_orange.setTextSize(60);*/

    }

    @Override
    public void onDraw(Canvas c) {
        //Need to add: Way to get info for drawings (location, height, width)
        createGrid(c);
        createTray(c);
        //createTile(c);

        Log.i("onDraw", "onDraw is drawing");
    }

    public void createTile(Canvas c){

        //float box_x = myPlayer.getBox_x();
        //float box_y = myPlayer.getBox_y();

        float box_x = 10;
        float box_y = 10;

        //Draws the tile (temp values change later)
        //c.drawRect(box_x + 10, box_y + 10, box_x + getWidth_tile() - 10, box_y + getHeight_tile() - 10, tilePaint);

        //draw tile test
        c.drawRect(box_x, box_y, getWidth_tile() - 10, getHeight_tile() - 10, tilePaint);
        c.drawCircle(80, 115, (getWidth_tile() / 4), tilePaint2);
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

        setHeight_grid(((float) getHeight() / 3) * 2);
        setWidth_grid(getWidth());

        // vertical lines

        int colCount = 13; //# of cols in grid
        for (int i = 0; i <= colCount; i++)
        {
            float pos = (width_grid / colCount) * (i);
            c.drawLine(pos, 0, pos, height_grid, gridPaint);
            //this.invalidate();
        }

        // horizontal lines

        int rowCount = 4; //# of rows in grid

        for (int i = 0; i <= rowCount; i++)
        {
            float pos = (height_grid / rowCount) * (i);
            c.drawLine(0, pos, width_grid, pos, gridPaint);
            //this.invalidate();
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
        int cntr = 0;

        for (int i = 0; i < colCount; i++) {

            for (int j = 0; j < rowCount; j++) {

                pos_x1 = (width_grid  / colCount) * (i);
                pos_y1 = (height_grid / rowCount) * (j);
                pos_x2 = (width_grid  / colCount) * (i + 1);
                pos_y2 = (height_grid / rowCount) * (j + 1);
                //System.out.println(pos_x2);

                if ((newState != null) && (cntr < newState.getBoard().size())) {

                    Tile tmp = newState.getBoard().get(cntr);

                    if (tmp.isVisible()) {
                        c.drawRect(pos_x1 + 10, pos_y1 + 10, pos_x2 - 10, pos_y2 - 10, getTilePaint());
                        c.drawCircle(pos_x1 + 62, pos_y1 + 60, (getWidth_tile() / 4), getTilePaint2());
                        createTileText(c, tmp);
                        c.drawText("" + tmp.getTileNum(), pos_x1 + 42, pos_y1 + 70, tileNumPaint);
                        //this.invalidate();
                    }

                    else if (!newState.getPlayerHand().get(0).get(cntr).isVisible()){
                        c.drawRect(pos_x1 + 10, pos_y1 + 10, pos_x2 - 10, pos_y2 - 10, getBoardPaint_invis());
                        //this.invalidate();
                    }

                //This section is for the tile numbers

                if ((i == 0) && (j == 0)) {
                    setWidth_tile(pos_x2);
                    setHeight_tile(pos_y2);
                }

                cntr++;
                //this.invalidate();
            }
        }
    }

    /* in charge of drawing the player's hand tray for the .xml */
    public void createTray(Canvas c) {

        setWidth_tray(getWidth() - 20);
        setHeight_tray((((float) getHeight() / 3) * 2) + 20);

        c.drawRect(20, height_tray, width_tray, height_tray + height_tray, trayPaint_invis);

        /**
         * Here we're essentially doing what we did earlier within the createGrid method.
         * This nested for-loop is making a 2x12 array of invisible "tile" drawings to be
         * colored in earlier with onTouch() methods (represents the hand)
        */

        int colCount = 12;
        int rowCount = 2;

        int cntr = 0;
        //float pos_x1, pos_y1, pos_x2, pos_y2;

        for (int i = 0; i < rowCount; i++) {

            for (int j = 0; j < colCount; j++) {

                pos_x1 = (getWidth_tile() * (j))        + 60;
                pos_y1 = (getHeight_tile() * (i))       + height_tray + 10;
                pos_x2 = (getWidth_tile() * (j + 1))    + 60;
                pos_y2 = (getHeight_tile() * (i + 1))   + height_tray + 10;

                if ((newState != null) && (cntr < newState.getPlayerHand().get(0).size())) {

                    Tile tmp = newState.getPlayerHand().get(0).get(cntr);

                    if (tmp.isVisible()) {
                        c.drawRect(pos_x1 + 10, pos_y1 + 10, pos_x2 - 10, pos_y2 - 10, getTilePaint());
                        c.drawCircle(pos_x1 + 62, pos_y1 + 60, (getWidth_tile() / 4), getTilePaint2());
                        createTileText(c, tmp);
                        c.drawText("" + tmp.getTileNum(), pos_x1 + 42, pos_y1 + 70, tileNumPaint);
                        //this.invalidate();
                    }

                    else if (!newState.getPlayerHand().get(0).get(cntr).isVisible()){
                        c.drawRect(pos_x1 + 10, pos_y1 + 10, pos_x2 - 10, pos_y2 - 10, getTrayPaint_invis());
                        //this.invalidate();
                    }
                }
                newState.toString();

                    cntr++;

                } else return;
            }
        }

        Log.i("createTray", "createTray is calling");
        Log.i("createTray", "# tiles in hand: " + newState.getPlayerHand().get(0).size());
        Log.i("createTray", "Player 0 Tiles: " + myPlayer);
        //this.invalidate();
    }

    public void createTileText(Canvas c, Tile t) {

        /*
        ArrayList<Tile> boardClone = newState.getBoard();

        //Get the tile from the board
        Tile t = boardClone.get((i+1)*j);

         */
        //Draw the text depending on the position and color of the tile
        switch (t.getColor()) {
            case 1:
                //black tiles
                tileNumPaint.setColor(tileColor_black);
                break;
            case 2:
                //blue tiles
                tileNumPaint.setColor(tileColor_blue);;
                break;
            case 3:
                //orange tiles
                tileNumPaint.setColor(tileColor_orange);
                break;
            case 4:
                //red tiles
                tileNumPaint.setColor(tileColor_red);
                break;
            default://In case something goes very wrong
                break;
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

    public float getWidth_grid() {
        return width_grid;
    }

    public void setWidth_grid(float width_grid) {
        this.width_grid = width_grid;
    }

    public float getHeight_grid() {
        return height_grid;
    }

    public void setHeight_grid(float height_grid) {
        this.height_grid = height_grid;
    }

    public float getWidth_tray() {
        return width_tray;
    }

    public void setWidth_tray(float width_tray) {
        this.width_tray = width_tray;
    }

    public float getHeight_tray() {
        return height_tray;
    }

    public void setHeight_tray(float height_tray) {
        this.height_tray = height_tray;
    }

    public Paint getTilePaint() {
        return tilePaint;
    }

    public void setTilePaint(Paint tilePaint) {
        this.tilePaint = tilePaint;
    }

    public Paint getTilePaint2() {
        return tilePaint2;
    }

    public void setTilePaint2(Paint tilePaint2) {
        this.tilePaint2 = tilePaint2;
    }

    public Paint getBoardPaint_invis() {
        return boardPaint_invis;
    }

    public void setBoardPaint_invis(Paint boardPaint_invis) {
        this.boardPaint_invis = boardPaint_invis;
    }

    public Paint getTrayPaint_invis() {
        return trayPaint_invis;
    }

    public void setTrayPaint_invis(Paint trayPaint_invis) {
        this.trayPaint_invis = trayPaint_invis;
    }

    public RummiGameState getGameState() { return newState; }
}