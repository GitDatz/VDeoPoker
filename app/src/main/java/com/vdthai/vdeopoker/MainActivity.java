package com.vdthai.vdeopoker;

import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements Presenter.View {

    private Presenter presenter;
    private List<ScoreBoard> handScores;

    /**
     * Drawable card icons in a matrix of drawables.
     */
    private static final int[][] DECK = {
            { R.drawable.two_diamonds, R.drawable.three_diamonds, R.drawable.four_diamonds, R.drawable.five_diamonds, R.drawable.six_diamonds, R.drawable.seven_diamonds, R.drawable.eight_diamonds, R.drawable.nine_diamonds, R.drawable.ten_diamonds, R.drawable.jack_diamonds, R.drawable.queen_diamonds, R.drawable.king_diamonds, R.drawable.ace_diamonds },
            { R.drawable.two_clubs, R.drawable.three_clubs, R.drawable.four_clubs, R.drawable.five_clubs, R.drawable.six_clubs, R.drawable.seven_clubs, R.drawable.eight_clubs, R.drawable.nine_clubs, R.drawable.ten_clubs, R.drawable.jack_clubs, R.drawable.queen_clubs, R.drawable.king_clubs, R.drawable.ace_clubs },
            { R.drawable.two_hearts, R.drawable.three_hearts, R.drawable.four_hearts, R.drawable.five_hearts, R.drawable.six_hearts, R.drawable.seven_hearts, R.drawable.eight_hearts, R.drawable.nine_hearts, R.drawable.ten_hearts, R.drawable.jack_hearts, R.drawable.queen_hearts, R.drawable.king_hearts, R.drawable.ace_hearts },
            { R.drawable.two_spades, R.drawable.three_spades, R.drawable.four_spades, R.drawable.five_spades, R.drawable.six_spades, R.drawable.seven_spades, R.drawable.eight_spades, R.drawable.nine_spades, R.drawable.ten_spades, R.drawable.jack_spades, R.drawable.queen_spades, R.drawable.king_spades, R.drawable.ace_spades }
    };

    /**
     * The id's of the card views.
     */
    private static final int[] CARD_FIELD = {
            R.id.cardViewOne, R.id.cardViewTwo, R.id.cardViewThree, R.id.cardViewFour, R.id.cardViewFive
    };

    /**
     * The id's of the hold text views.
     */
    private static final int[] HOLD_FIELD = {
            R.id.holdTxt1, R.id.holdTxt2, R.id.holdTxt3, R.id.holdTxt4, R.id.holdTxt5
    };

    /**
     * Clears the hold, both button and in array.
     */
    public void clearHold(){
        for( int i = 0; i < 5; i++ ){
            TextView imgView = (TextView)findViewById(HOLD_FIELD[i]);
            imgView.setText("");
        }
    }

    /**
     * Hold a card in a given position.
     * @param _cardPos the card position to hold.
     */
    public void setHold( int _cardPos ){
        presenter.holdCards( _cardPos );
        TextView holdTxt = (TextView)findViewById(HOLD_FIELD[_cardPos]);
        if( holdTxt.getText().equals("HOLD") ){
            holdTxt.setText("");
        } else {
            holdTxt.setText( R.string.holdText );
        }
    }

    /**
     * Updates the cash view.
     */
    public void updateCash(){
        TextView cashView = (TextView)findViewById( R.id.cashView );
        cashView.setText( "CASH: $" + Integer.toString( presenter.getCash() ) );
    }

    /**
     * Updated the bet view.
     * @param bet the bet.itt
     */
    public void updateBet( int bet ){
        TextView betView = (TextView)findViewById( R.id.betView );
        betView.setText( "BET: $" + Integer.toString( bet ) );
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter = new Presenter(this);

        handScores = new ArrayList<>();
        ListView scoreListView = (ListView)findViewById( R.id.scoreListView );
        ScoreViewAdapter scoreViewAdapter = new ScoreViewAdapter( this, handScores );
        scoreListView.setAdapter( scoreViewAdapter );
        fillScoreBoard();

        updateCash();
        updateBet( 1 );

        Button dealButton = (Button)findViewById(R.id.dealButton);
        dealButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                List<Card> dealCards = presenter.dealCards();
                for( int i = 0; i < dealCards.size(); i++ ){
                    ImageView imgView = (ImageView)findViewById( CARD_FIELD[i] );
                    imgView.setImageResource( DECK[ dealCards.get(i).getSuitRank()-1 ][ dealCards.get(i).getRank()-1 ] );
                }
                // Check if round has ended.
                if( presenter.checkRound() ){
                    AlertDialog.Builder winBuilder = new AlertDialog.Builder( MainActivity.this );
                    winBuilder.setTitle( "Win! " + presenter.getResultString() );
                    winBuilder.setMessage( "Do you want to double?" );
                    winBuilder.setCancelable( true );
                    winBuilder.setPositiveButton(android.R.string.yes, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            // Call double game
                            dialogInterface.cancel();
                        }
                    });
                    winBuilder.setNegativeButton(android.R.string.no, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialogInterface, int i) {
                            dialogInterface.cancel();
                        }
                    });
                    AlertDialog winDialog = winBuilder.create();
                    winDialog.show();
                }
                updateCash();
            }
        });

        /**
         * Set onClickListener on the image views (cards).
         */
        ImageView holdButton1 = (ImageView) findViewById(R.id.cardViewOne);
        holdButton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHold( 0 );
            }
        });

        ImageView holdButton2 = (ImageView)findViewById(R.id.cardViewTwo);
        holdButton2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHold( 1 );
            }
        });

        ImageView holdButton3 = (ImageView)findViewById(R.id.cardViewThree);
        holdButton3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHold( 2 );
            }
        });

        ImageView holdButton4 = (ImageView)findViewById(R.id.cardViewFour);
        holdButton4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHold( 3 );
            }
        });

        ImageView holdButton5 = (ImageView)findViewById(R.id.cardViewFive);
        holdButton5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                setHold( 4 );
            }
        });

        /**
         * Set onClickListener for the Bet One button.
         */
        Button betOneButton = (Button)findViewById(R.id.betOneButton);
        betOneButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBet( presenter.betOne() );
            }
        });

        /**
         * Set onClickListener for the Bet Max button.
         */
        Button betMaxButton = (Button)findViewById(R.id.betMaxButton);
        betMaxButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateBet( presenter.betMax() );
            }
        });
    }

    /**
     * Fills the score board with content.
     */
    private void fillScoreBoard(){
        ScoreBoard royalFlush, straightFlush, fourOfKind, fullHouse, flush, straight, threeOfKind, twoPair, jacksOrBetter;

        royalFlush = new ScoreBoard("Royal Flush", 250, 500, 750, 1000 );
        handScores.add( royalFlush );

        straightFlush = new ScoreBoard("Straight Flush", 150, 300, 450, 600 );
        handScores.add( straightFlush );

        fourOfKind = new ScoreBoard("Four of a Kind", 100, 200, 300, 400 );
        handScores.add( fourOfKind );

        fullHouse = new ScoreBoard("Full House", 50, 100, 150, 200 );
        handScores.add( fullHouse );

        flush = new ScoreBoard("Flush", 25, 50, 75, 100 );
        handScores.add( flush );

        straight = new ScoreBoard("Straight", 10, 20, 30, 40 );
        handScores.add( straight );

        threeOfKind = new ScoreBoard("Three of a Kind", 5, 10, 15, 20 );
        handScores.add( threeOfKind );

        twoPair = new ScoreBoard("Two Pair", 3, 6, 9, 12 );
        handScores.add( twoPair );

        jacksOrBetter = new ScoreBoard("Jacks or Better", 1, 2, 3, 4 );
        handScores.add( jacksOrBetter );
    }
}
