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

    //Default constructor for Tile
    public Tile(int init_tileColor, int init_tileNum) {

        if(0 == init_tileColor) {
            if (0 != init_tileNum) {
                throw new IllegalArgumentException();
            }

        } else if (0 == init_tileNum) {
            throw new IllegalArgumentException();
        }

        this.tileColor = init_tileColor;
        this.tileNum = init_tileNum;
    }

    //Copy constructor for Tile
    public Tile(Tile orig) {
        this.tileColor = orig.tileColor;
        this.tileNum = orig.tileNum;
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

    @Override
    public String toString() {
        return tileColor + " " + tileNum;
    }
}
