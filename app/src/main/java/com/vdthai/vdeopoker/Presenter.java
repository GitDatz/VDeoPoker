package com.vdthai.vdeopoker;

import android.content.Intent;
import java.util.List;

/**
 * Created by vdthai on 2016-10-07.
 */

class Presenter {
    VDeoPokerView view;
    Game game;
    MainActivity mainActivity;

    /**
     * Constructor for the presenter.
     * @param mainActivity
     * @param view
     */
    Presenter( MainActivity mainActivity, VDeoPokerView view ){
        this.mainActivity = mainActivity;
        this.view = view;
        game = new Game();
    }

    /**
     * Deal cards for a round.
     * @return a hand of cards.
     */
    List<Card> dealCards(){
        return game.deal();
    }

    /**
     * Getter for a player's cash.
     * @return the player's current cash.
     */
    int getCash(){
        return game.getCash();
    }

    /**
     * Setter for win amount.
     * @param winSum the amount won.
     */
    void setWinSum( int winSum ){
        game.setCash( winSum );
    }

    /**
     * Increases bet by 1.
     * @return the current bet.
     */
    int betOne(){
        return game.setBet();
    }

    /**
     * Set bet max.
     * @return max amount to bet.
     */
    int betMax(){
        return game.setBetMax();
    }

    /**
     * Sets the cards to keep (hold).
     * Valid positions: 0 .. 4
     * @param _holdPos the position for the card to hold.
     */
    void holdCards( int _holdPos ){
        game.holdCard( _holdPos );
    }

    /**
     * Check if the final hand is a winning hand.
     */
    void checkRound(){
        if( !game.isEndGame() ){
            view.clearHold();
            view.updateCash();
        } else {
            if( game.checkHand() ){
                Intent intent = new Intent(mainActivity, DoubleUpActivity.class);
                // Send information to Double Up activity
                intent.putExtra("cash", game.getCash());
                intent.putExtra("winHand", game.getResultString());
                intent.putExtra("winSum", game.getWinSum());
                intent.putIntegerArrayListExtra("handRanks", game.getHandRanks());
                intent.putIntegerArrayListExtra("handSuits", game.getHandSuits());
                //mainActivity.startActivity( intent );
                //mainActivity.startActivityForResult( intent, 1 );
                view.startDoubleUpGame( intent );
            }
        }
    }

    /**
     * Makes this an interface for the View to implement.
     */
    interface VDeoPokerView {
        void clearHold();
        void startDoubleUpGame( Intent intent );
        void updateCash();
    }
}
