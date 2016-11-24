package com.vdthai.vdeopoker;

/**
 * Created by vdthai on 2016-11-14.
 */

public class DoubleUpPresenter {
    View view;
    DoubleUpGame doubleUpGame;

    /**
     * Constructor for DoubleUpPresenter class.
     * @param view
     * @param doubleAmount
     */
    DoubleUpPresenter( View view, int doubleAmount ){
        this.view = view;
        doubleUpGame = new DoubleUpGame( doubleAmount );
    }

    /**
     * Deal cards. Return dealer's card.
     * @return dealer's card.
     */
    Card dealCards(){
        return doubleUpGame.deal();
    }

    /**
     * Gets card that the player chose.
     * @param cardPos position of card in the list.
     * @return the card chosen by the player.
     */
    Card getCard( int cardPos ){
        return doubleUpGame.chooseCard( cardPos );
    }

    /**
     * Returns amount won after double up.
     * @return amount won.
     */
    int getWin(){
        return doubleUpGame.getWinAmount();
    }

    /**
     * Check if player beat dealer.
     * @return true if player won.
     */
    boolean checkWin(){
        return doubleUpGame.compareCards( doubleUpGame.getChosenCard() );
    }

    /**
     * Makes this an interface for the View to implement.
     */
    interface View {
    }
}
