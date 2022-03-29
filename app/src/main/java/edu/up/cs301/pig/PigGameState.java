package edu.up.cs301.pig;

import edu.up.cs301.game.infoMsg.GameState;

public class PigGameState extends GameState {

    private int id;
    private int player_0;
    private int player_1;
    private int running_total;
    private int die;

    public PigGameState(){
        this.id = 0;
        this.player_0 = 0;
        this.player_1 = 0;
        this.running_total = 0;
        this.die = 0;

    }
    // Copy Constructor
    public PigGameState(PigGameState copy){
        this.id = copy.getId();
        this.player_0 = copy.getPlayer_0();
        this.player_1 = copy.getPlayer_1();
        this.running_total = copy.getRunning_total();
        this.die = copy.getDie();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlayer_0() {
        return player_0;
    }

    public void setPlayer_0(int player_0) {
        this.player_0 = player_0;
    }

    public int getPlayer_1() {
        return player_1;
    }

    public void setPlayer_1(int player_1) {
        this.player_1 = player_1;
    }

    public int getRunning_total() {
        return running_total;
    }

    public void setRunning_total(int running_total) {
        this.running_total = running_total;
    }

    public int getDie() {
        return die;
    }

    public void setDie(int die) {
        this.die = die;
    }
}
