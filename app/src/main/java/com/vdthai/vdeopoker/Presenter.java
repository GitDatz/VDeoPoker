package com.vdthai.vdeopoker;

import java.util.List;

/**
 * Created by vdthai on 2016-10-07.
 */

class Presenter {
    View view;
    Game game;

    /**
     * Constructor for the presenter.
     * @param _view the view.
     */
    Presenter( View _view ){
        view = _view;
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
     * Getter for the round's result.
     * @return string of the hand. Empty if false.
     */
    String getResultString(){
        return game.getResultString();
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
     * @return pair of int (amount of cash won) and string (winning hand).
     */
    Boolean win(){
        return game.checkHand();
    }

    /**
     * Checks if the round has ended.
     * @return true if game has ended.
     */
    boolean endGame() {
        return game.isEndGame();
    }

    /**
     * Makes this an interface for the View to implement.
     */
    interface View {

    }
}
