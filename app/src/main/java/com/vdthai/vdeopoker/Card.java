package com.vdthai.vdeopoker;

/**
 * Created by vdthai on 2016-10-03.
 */

class Card {

    /**
     * Enum Suit: used as suits for the Card class.
     */
    enum Suit {
        SPADES("SPADES", 4), HEARTS("HEARTS", 3), CLUBS("CLUBS", 2), DIAMONDS("DIAMONDS", 1);

        private String suit;
        private int suitRank;

        /**
         * Constructor for the Suit enum.
         * @param _suit the suit of the enum in String.
         * @param _suitRank the suit of the enum as an int.
         */
        Suit( String _suit, int _suitRank ){
            suit = _suit;
            suitRank = _suitRank;
        }

        /**
         * Getter for the suit in String.
         * @return string of the suit.
         */
        public String getSuit(){
            return suit;
        }

        /**
         * Getter for the suit as an int.
         * @return int of the suit.
         */
        public int getSuitRank(){
            return suitRank;
        }
    }

    /**
     * Enum Rank: used as ranks for the Card class.
     */
    enum Rank {
        ACE(1), DEUCE(2), THREE(3), FOUR(4), FIVE(5), SIX(6), SEVEN(7), EIGHT(8), NINE(9),
        TEN(10), JACK(11), QUEEN(12), KING(13);

        private int rank;

        /**
         * Constructor for the Rank enum.
         * @param _rank the rank of the enum.
         */
        Rank( int _rank ){
            rank = _rank;
        }

        /**
         * Getter for the rank.
         * @return the rank.
         */
        public int getRank(){
            return rank;
        }
    }

    private Suit suit;
    private Rank rank;

    /**
     * Constructor for the Card class.
     * @param _rank rank of the card.
     * @param _suit suit of the card.
     */
    Card( Rank _rank, Suit _suit ){
        suit = _suit;
        rank = _rank;
    }

    /**
     * Getter for the rank of the card.
     * @return the rank of the card.
     */
    int getRank(){
        return rank.getRank();
    }

    /**
     * Getter for the suit as an int.
     * @return int of the suit of the card.
     */
    int getSuitRank(){
        return suit.getSuitRank();
    }

    /**
     * Getter for the suit in String.
     * @return a String of the suit of the card.
     */
    private String getSuit(){
        return suit.getSuit();
    }
}
