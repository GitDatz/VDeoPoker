package com.vdthai.vdeopoker;

/**
 * Created by vdthai on 2016-10-30.
 */

public class ScoreBoard {
    private String hand;
    private int betOne;
    private int betTwo;
    private int betThree;
    private int betFour;

    /**
     * Constructor for a scoreboard.
     * @param _hand the winning hand.
     * @param _betOne winning amount when bet = 1.
     * @param _betTwo winning amount when bet = 2.
     * @param _betThree winning amount when bet = 3.
     * @param _betFour winning amount when bet = 4.
     */
    public ScoreBoard( String _hand, int _betOne, int _betTwo, int _betThree, int _betFour ){
        hand = _hand;
        betOne = _betOne;
        betTwo = _betTwo;
        betThree = _betThree;
        betFour = _betFour;
    }

    /**
     * Individual getters for the ScoreBoard class.
     */

    public String getHand(){
        return hand;
    }

    public int getBetOne(){
        return betOne;
    }

    public int getBetTwo(){
        return betTwo;
    }

    public int getBetThree(){
        return betThree;
    }

    public int getBetFour(){
        return betFour;
    }
}
