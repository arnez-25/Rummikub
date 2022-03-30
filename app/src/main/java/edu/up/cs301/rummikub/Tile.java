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

public class Tile {
    private int color;
    private int tileNum;

    //Default constructor for Tile
    public Tile(int init_color, int init_tileNum) {
        this.color = init_color;
        this.tileNum = init_tileNum;
    }

    //Tile constructor
    public Tile(){
        this.color = Color.BLACK;
        this.tileNum = 1;
    }

    //Copy constructor for Tile
    public Tile(Tile orig) {
        this.color = orig.color;
        this.tileNum = orig.tileNum;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public int getTileNum() {
        return tileNum;
    }

    public void setTileNum(int tileNum) {
        this.tileNum = tileNum;
    }

    @Override
    public String toString() {
        return color + " " + tileNum;
    }
}
