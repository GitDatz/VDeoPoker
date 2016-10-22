package com.vdthai.vdeopoker;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vdthai on 2016-10-08.
 */

class Game {

    /**
     * Enum GAME_STATE: keep the state of the game.
     */
    enum GAME_STATE{
        INIT, DEAL
    }

    private Deck deck;
    private List<Card> cardList;
    private List<Boolean> holdCards;
    private int cash;
    private int bet;
    private GAME_STATE gameState;

    /**
     * Constructor for the Game class.
     */
    Game(){
        deck = new Deck();
        deck.shuffle();
        gameState = GAME_STATE.INIT;
        cardList = new ArrayList<>(5);
        cash = 100;
        bet = 1;
        holdCards = new ArrayList<>();
        for( int i = 0; i < 5; i++ ){
            holdCards.add( i, false );
        }
    }

    /**
     * Initializes the game.
     */
    private void initGame(){
        gameState = GAME_STATE.INIT;
        reShuffle();
        for( int i = 0; i < 5; i++ ){
            holdCards.set( i, false );
        }
    }

    /**
     * Deal initial 5 cards.
     * @return list of cards dealt.
     */
    List<Card> deal(){
        if( gameState == GAME_STATE.INIT ){
            cardList.clear();
            for( int i = 0; i < 5; i++ ){
                cardList.add( deck.drawCard() );
            }
            setCash( -bet );
            gameState = GAME_STATE.DEAL;
        } else {
            for( int i = 0; i < 5; i++ ){
                if( !holdCards.get(i) ){
                    cardList.set( i, deck.drawCard() );
                }
            }
            initGame();
        }
        return cardList;
    }

    /**
     * Setter for the player's cash.
     * @param _cash the amount cash to be set.
     */
    private void setCash( int _cash ){
        cash += _cash;
    }

    /**
     * Getter for the player's cash.
     * @return the player's cash.
     */
    int getCash(){
        return cash;
    }

    /**
     * Setter for bet one.
     * Valid bets: 1, 2, 3, 4, 5
     * @return amount to how much to bet.
     */
    int setBet(){
        bet++;
        if( bet > 5 ){
            bet = 1;
        }
        return bet;
    }

    /**
     * Setter for bet max.
     * @return max bet allowed.
     */
    int setBetMax(){
        bet = 5;
        return bet;
    }

    /**
     * Re-shuffles the deck.
     */
    private void reShuffle(){
        deck.reShuffle();
    }

    /**
     * Hold the card given a card position.
     * @param _cardPos the card position.
     */
    void holdCard( int _cardPos ){
        if( holdCards.get( _cardPos ) ){
            holdCards.set( _cardPos, false );
        } else {
            holdCards.set( _cardPos, true );
        }
    }

    /**
     * Checks if the game round has ended.
     * @return true if game has ended.
     */
    boolean isEndGame(){
        return ( gameState == GAME_STATE.INIT );
    }

    /**
     * Function to check the hand.
     * Algorithm: Keep a list of suits and a list of ranks filled with 0.
     *            Then check if there exists any given number in the lists.
     *            Ex: if suitList contains 5, that means that there is at least
     *            a flush.
     * @return pair of integer (amount won) and string (winning hand).
     */
    Pair<Integer, String> checkHand() {
        //List<Integer> rankList = Arrays.asList(0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0);
        List<Integer> rankList = new ArrayList<>();
        List<Integer> suitList = new ArrayList<>();
        String currentHand = "";
        int winCash = 0;

        /**
         * Initialize the lists.
         */
        for( int i = 0; i < 14; i++ ){
            rankList.add(i,0);
        }

        for( int i = 0; i < 5; i++ ){
            suitList.add(i,0);
        }

        /**
         * Loop the cardList and increment based on the rank and suit.
         * Check the Card class for the valid values.
         */
        for( Card card : cardList ){
            rankList.set(card.getRank(), rankList.get(card.getRank()) + 1);
            suitList.set(card.getSuitRank(), rankList.get(card.getSuitRank()) + 1);
        }

        /**
         * Start to check the hand for combinations.
         */
        if( isStraight() ){
            if( suitList.contains(5) ){
                if( rankList.get(0) == 1 && rankList.get(12) == 1 ){
                    currentHand = "ROYAL FLUSH";
                    winCash += 250;
                } else {
                    currentHand = "STRAIGHT FLUSH";
                    winCash += 150;
                }
            } else {
                currentHand = "STRAIGHT";
                winCash += 20;
            }
        } else if( suitList.contains(5)) {
            currentHand = "FLUSH";
            winCash += 35;
        } else if( rankList.contains(4) ){
            currentHand = "FOUR OF A KIND";
            winCash += 100;
        } else if( rankList.contains(3) && rankList.contains(2) ){
            currentHand = "FULL HOUSE";
            winCash += 50;
        } else if( rankList.contains(3) ){
            currentHand = "THREE OF A KIND";
            winCash += 10;
        } else if( rankList.contains(2) && ( Collections.frequency( rankList, 2 ) > 1 ) ){ // Two pair?
            currentHand = "TWO PAIR";
            winCash += 5;
        } else if( rankList.contains(2) ){ // Get value of element in array that contains 2, compare to JJ
            if( rankList.indexOf( 2 ) == 0 || rankList.indexOf( 2 ) > 9 ){
                currentHand = "JACKS OR BETTER";
                winCash += 1;
            }
        }
        setCash( winCash );
        return new Pair<Integer, String>( winCash, currentHand );
    }

    /**
     * Check if the hand is a straight.
     * Algorithm: Add the ranks of the hand to a list. Sort the list
     *            descending and check if it is a straight.
     * @return true if the hand contains a straight.
     */
    private Boolean isStraight(){
        List<Integer> tmpList = new ArrayList<>();
        for( Card card : cardList ){
            tmpList.add( card.getRank() );
        }
        Collections.sort( tmpList );
        for( int i = 0; i < 4; i++ ){
            if( tmpList.get( i )+1 != tmpList.get( i+1 ) ){
                return false;
            }
        }
        return true;
    }
}
