package edu.up.cs301.rummikub;

import android.view.MotionEvent;
import android.view.View;
import android.widget.CompoundButton;

/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 3/23/2022
 *
 *
 *
 * Bugs: Issues with GameState object. (3/23)
 *
 * */

public class RummiController implements View.OnClickListener, CompoundButton.OnCheckedChangeListener, View.OnTouchListener{
    //Initializing RummiView and Gamestate objects
    private final RummiView rView;
    private RummiGameState game;

    //Constructor
    public RummiController(RummiView view){
        this.rView = view;
        this.game = view.getGameState();
    }

    @Override
    public void onCheckedChanged(CompoundButton compoundButton, boolean b) {

    }

    @Override
    public void onClick(View view) {

    }

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
        return false;
    }
}
