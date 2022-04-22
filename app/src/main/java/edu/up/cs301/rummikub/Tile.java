package edu.up.cs301.rummikub;

import android.graphics.Color;

/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 3/17/2022
 * (Class Description)
 *
 * Bugs: We have two tile constructors (3/17/2022)
 *
 * */

public class Tile implements Cloneable{
    private int tileColor;
    private int tileNum;
    private boolean visible;

    public static final Tile BLANK_TILE = new Tile(0,0, false);    //Acts as a Null tile universal; Helped with Nuxoll

    /**
     * Default constructor for tile class
     *
     * @param init_tileColor
     * @param init_tileNum
     * @param init_visible
     */

    public Tile(int init_tileColor, int init_tileNum, boolean init_visible) {

        if(0 == init_tileColor) {
            if (0 != init_tileNum) {
                throw new IllegalArgumentException();
            }

        } else if (0 == init_tileNum) {
            throw new IllegalArgumentException();
        }

        this.tileColor = init_tileColor;
        this.tileNum = init_tileNum;
        this.visible = init_visible;
    }


    /**
     * Constructor for deep copy of Tiles
     *
     * @param orig
     */
    public Tile(Tile orig) {
        this.tileColor = orig.tileColor;
        this.tileNum = orig.tileNum;
        this.visible = orig.visible;
    }

    //Method from implementation to allow for deepcopy
    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }

    //Getters and Setters
    public int getColor() {
        return tileColor;
    }

    public void setColor(int color) {
        this.tileColor = color;
    }

    public int getTileNum() {
        return tileNum;
    }

    public void setTileNum(int tileNum) {
        this.tileNum = tileNum;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    @Override
    public String toString() {
        return tileColor + " " + tileNum;
    }
}
