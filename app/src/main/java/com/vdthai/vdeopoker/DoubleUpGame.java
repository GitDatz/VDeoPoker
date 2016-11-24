package com.vdthai.vdeopoker;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by vdthai on 2016-11-14.
 */

public class DoubleUpGame {
    /**
     * Enum GAME_STATE: keep the state of the game.
     */
    enum GAME_STATE{
        INIT, DEAL
    }

    private GAME_STATE gameState; // To guard against extra presses!
    private Deck deck;
    private List<Card> cardList;
    private int doubleAmount;
    private int winAmount;
    private Card chosenCard; // Boolean == True if card has been selected

    /**
     * Constructor for DoubleUpGame class.
     * @param doubleAmount won amount.
     */
    public DoubleUpGame( int doubleAmount ){
        deck = new Deck();
        deck.shuffle();
        cardList = new ArrayList<>();
        chosenCard = new Card();
        this.doubleAmount = doubleAmount;
        winAmount = 0;
        gameState = GAME_STATE.INIT;
    }

    /**
     * Deal initial 5 cards.
     * @return list of cards dealt.
     */
    Card deal(){
        cardList.clear();
        if( cardList.size() > 0 ) {
            for (int i = 0; i < 5; i++) {
                cardList.set(i, deck.drawCard());
            }
        } else {
            for (int i = 0; i < 5; i++) {
                cardList.add(deck.drawCard());
            }
        }
        return cardList.get(0);
    }

    /**
     * Pick a card against the dealer.
     * @param cardPos the position of the card chosen.
     * @return the card chosen.
     */
    Card chooseCard( int cardPos ){
        chosenCard = cardList.get( cardPos );
        return chosenCard;
    }

    /**
     * Return chosen card.
     * @return chosen card.
     */
    Card getChosenCard(){
        return chosenCard;
    }

    /**
     * Getter for the amount won.
     * @return amount of cash won.
     */
    int getWinAmount(){
        return winAmount;
    }

    /**
     * Compare chosen card to the dealer's card.
     * @param chosenCard player's chosen card.
     * @return true if player's card beats dealer's card.
     */
    boolean compareCards( Card chosenCard ){
        if( chosenCard.getRank() > cardList.get(0).getRank() ){
            // Ugly solution for now
            winAmount = doubleAmount * 2;
            doubleAmount = winAmount;
            return true;
        }
        return false;
    }
}
