package com.vdthai.vdeopoker;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Created by vdthai on 2016-10-03.
 */

class Deck {
    private List<Card> deck;

    /**
     * Constructor for the Deck class.
     */
    Deck(){
        deck = new ArrayList<>();
        // Add cards to the deck
        initDeck();
    }

    /**
     * Fill the deck with cards.
     */
    private void initDeck(){
        for( Card.Suit suit : Card.Suit.values() ){
            for( Card.Rank rank : Card.Rank.values() ){
                deck.add( new Card( rank, suit ) );
            }
        }
    }

    /**
     * Re-shuffle the deck.
     */
    void reShuffle(){
        deck.clear();
        initDeck();
        shuffle();
    }

    /**
     * Shuffle the deck.
     */
    void shuffle(){
        Collections.shuffle( deck );
    }

    /**
     * Draw a card from the deck.
     * @return the top card in the deck.
     */
    Card drawCard(){
        Card newCard;
        //Is there a more efficient way? This = O(n)
        if( deck.size() > 0 ){
            newCard = deck.get(0);
            deck.remove(0);
        } else {
            newCard = new Card();
        }
        return newCard;
    }
}
