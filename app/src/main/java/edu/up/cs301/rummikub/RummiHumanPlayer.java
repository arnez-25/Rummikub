package edu.up.cs301.rummikub;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import edu.up.cs301.game.GameHumanPlayer;
import edu.up.cs301.game.GameMainActivity;
import edu.up.cs301.game.R;
import edu.up.cs301.game.infoMsg.GameInfo;


/**
 * @authors Jacob Arnez, Maja Elliott, Dylan Kim, Chase Ohmstede
 * @version 3/30/2022
 *
 * (insert description)
 *
 * Bugs:
 *
 * */

public class RummiHumanPlayer extends GameHumanPlayer implements View.OnTouchListener, View.OnClickListener {

    /* time to make all the instance variables !!!! */
    private TextView    textview_timer      = null; //(to be continued)
    private TextView    textview_oppo       = null;
    private TextView    textview_deck       = null;

    private Button      button_quit         = null;
    private Button      button_draw         = null;
    private Button      button_undo         = null;
    private Button      button_done         = null;

    /* this is the android activity we're running */
    private GameMainActivity myActivity;
    private RummikubGameState pState;

    /**
     * constructor does nothing extra
     */
    public RummiHumanPlayer(String name) {
        super(name);
    }

    /**
     *  originally was 'return null', check later
     */
    @Override
    public View getTopView() {
        return myActivity.findViewById(R.id.top_gui_layout);
    }

    @Override
    public void receiveInfo(GameInfo info) {
        pState = (RummikubGameState) info;

        textview_timer.setText("100s");
        textview_oppo.setText( "Opponent's Hand Count: " + pState.getPlayerHand().size());
        textview_deck.setText("Deck: " + pState.getDeck().size());

        /* below is code from finished PigHumanPlayer
        if (pState.getPlayerId() == 0) {
            playerTextView.setText(name + "'s Score:");
            oppTextView.setText("Opponent's Score:");
            playerScoreTextView.setText(pState.getScore1());
            oppScoreTextView.setText(pState.getScore2());
            turnTotalTextView.setText(pState.getScoreHold1());
        }
        else if (pState.getPlayerId() == 1) {
            playerTextView.setText("Opponent's Score:");
            oppTextView.setText(name + "'s Score:");
            playerScoreTextView.setText(pState.getScore2());
            oppScoreTextView.setText(pState.getScore1());
            turnTotalTextView.setText(pState.getScoreHold2());
        }
        messageTextView.setText(pState.getMessage());

        switch (pState.getDieVal()){
            case 1:
                dieImageButton.setImageResource(R.drawable.face1);
            case 2:
                dieImageButton.setImageResource(R.drawable.face2);
            case 3:
                dieImageButton.setImageResource(R.drawable.face3);
            case 4:
                dieImageButton.setImageResource(R.drawable.face4);
            case 5:
                dieImageButton.setImageResource(R.drawable.face5);
            case 6:
                dieImageButton.setImageResource(R.drawable.face6);
        } */

    }//receiveInfo

    /**
     * this method gets called when the user clicks the die or hold button. It
     * creates a new PigRollAction or PigHoldAction and sends it to the game.
     *
     * @param button
     * 		the button that was clicked
     */
    public void onClick(View button) {

        if (button.getId() == R.id.button_quit) {
            System.exit(0);
        }

        else if (button.getId() == R.id.button_draw) {
            game.sendAction(new DrawTile(this));
        }

        else if (button.getId() == R.id.button_undo) {
            //game.sendAction(new Undo(this)); (saving undo for beta testing)
        }

        else if (button.getId() == R.id.button_done) {
            game.sendAction(new EndTurn(this));
        }

        /* below is code from finished PigHumanPlayer
        if (button.getId() == R.id.dieButton) {
            game.sendAction(new PigRollAction(this));
        }
        else if (button.getId() == R.id.holdButton) {
            game.sendAction(new PigHoldAction(this));
        } */
    }// onClick

    @Override
    public void setAsGui(GameMainActivity activity) {

        // remember the activity
        myActivity = activity;

        // load the layout resource for our GUI
        activity.setContentView(R.layout.rummikub_layout);

        // initialize the widget reference member variables
        // first we do the textviews
        this.textview_timer = (TextView)activity.findViewById(R.id.textview_timer);
        this.textview_oppo = (TextView)activity.findViewById(R.id.textview_oppo);
        this.textview_deck = (TextView)activity.findViewById(R.id.textview_deck);

        // now comes the buttons
        this.button_quit = (Button)activity.findViewById(R.id.button_quit);
        this.button_draw = (Button)activity.findViewById(R.id.button_draw);
        this.button_undo = (Button)activity.findViewById(R.id.button_undo);
        this.button_done = (Button)activity.findViewById(R.id.button_done);

        // setting listeners for buttons
        button_quit.setOnClickListener(this);
        button_draw.setOnClickListener(this);
        button_undo.setOnClickListener(this);
        button_done.setOnClickListener(this);

    } //setAsGui

    @Override
    public boolean onTouch(View view, MotionEvent motionEvent) {
       int x = (int) motionEvent.getX();
       int y = (int) motionEvent.getY();
       int box_x = 0;
       int box_y = 0;

       //Hitboxes for tiles
       for (int i = 0; i < ((float) view.getHeight() / 3) * 2; i += (view.getWidth()/4)) {
           for (int j = 0; j < view.getWidth(); j += (view.getWidth()/13)) {
               if (x < i && x >= i + (view.getWidth()/4) && y < j && y >= j + (view.getWidth()/13)) {
                   box_x = i / (view.getWidth()/4);
                   box_y = j / (view.getWidth()/13);
               }
           }
       }

       return false;
    }
}
