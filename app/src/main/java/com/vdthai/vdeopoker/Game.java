package com.vdthai.vdeopoker;

import android.util.Log;
import android.util.Pair;

import java.util.ArrayList;
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
    private CheckHand checkHand;
    private List<Card> cardList;
    private List<Boolean> holdCards;
    private int cash;
    private int bet;
    private int winSum;
    private GAME_STATE gameState;
    private String resultString;

    /**
     * Constructor for the Game class.
     */
    Game(){
        deck = new Deck();
        checkHand = new CheckHand();
        deck.shuffle();
        gameState = GAME_STATE.INIT;
        cardList = new ArrayList<>();
        cash = 100;
        bet = 1;
        winSum = 0;
        holdCards = new ArrayList<>();
        for( int i = 0; i < 5; i++ ){
            holdCards.add( i, false );
        }
        resultString = "";
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
        resultString = "";
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
     * @param cash the amount cash to be set.
     */
    void setCash( int cash ){
        this.cash += cash;
    }

    /**
     * Getter for the player's cash.
     * @return the player's cash.
     */
    int getCash(){
        return cash;
    }

    /**
     * Setter for the amounts won.
     * @param winSum the amount of cash won.
     */
    void setWinSum( int winSum ){
        Log.d("DEBUG", "SetWinSum!");
        this.winSum = winSum;
    }

    /**
     * Getter for the amounts won.
     * @return the amount cash won.
     */
    int getWinSum(){
        return winSum;
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
     * Getter for the result string.
     * @return string based on winning hand.
     */
    String getResultString(){
        return resultString;
    }

    /**
     * Re-shuffles the deck.
     */
    private void reShuffle(){
        deck.reShuffle();
    }

    /**
     * Get ranks of the player's hand. For displaying correct cards.
     * @return ArrayList of ranks.
     */
    ArrayList<Integer> getHandRanks(){
        ArrayList<Integer> handRankList = new ArrayList<>();
        for( Card card : cardList ){
            handRankList.add( card.getRank() );
        }
        return handRankList;
    }

    /**
     * Get suit values of the player's hand. For displaying correct cards.
     * @return ArrayList of suit values.
     */
    ArrayList<Integer> getHandSuits(){
        ArrayList<Integer> handSuitList = new ArrayList<>();
        for( Card card : cardList ){
            handSuitList.add( card.getSuitRank() );
        }
        return handSuitList;
    }

    /**
     * Hold the card given a card position.
     * @param cardPos the card position.
     */
    void holdCard( int cardPos ){
        if( holdCards.get( cardPos ) ){
            holdCards.set( cardPos, false );
        } else {
            holdCards.set( cardPos, true );
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
     * Function call to check the hand.
     * @return true if winning hand.
     */
    Boolean checkHand() {
        Pair<Integer, String> handPair = checkHand.checkHand( cardList );
        if( handPair.first > 0 ){
            resultString = handPair.second;
            setCash( handPair.first );
            setWinSum( handPair.first );
            return true;
        }
        return false;
    }
}
