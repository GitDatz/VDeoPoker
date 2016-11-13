package com.vdthai.vdeopoker;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by vdthai on 2016-11-11.
 */

public class DoubleUpActivity extends AppCompatActivity {
    private int cash;
    private int winSum;
    private String winHand;
    private ArrayList<Integer> handRanks;
    private ArrayList<Integer> handSuits;

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
            R.id.doubleCardViewOne, R.id.doubleCardViewTwo, R.id.doubleCardViewThree, R.id.doubleCardViewFour, R.id.doubleCardViewFive
    };

    /**
     * Sets winning hand to show initially.
     */
    void setWinningHand(){
        for( int i = 0; i < handRanks.size(); i++ ){
            ImageView imgView = (ImageView)findViewById( CARD_FIELD[i] );
            imgView.setImageResource( DECK[ handSuits.get(i) - 1 ][ handRanks.get(i) - 1 ] );
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double);
        handRanks = new ArrayList<>();
        handSuits = new ArrayList<>();

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            cash = extras.getInt("cash");
            winSum = extras.getInt("win");
            winHand = extras.getString("winHand");
            handRanks = extras.getIntegerArrayList("handRanks");
            handSuits = extras.getIntegerArrayList("handSuits");
        }
        TextView cashView = (TextView) findViewById(R.id.cashViewDouble);
        cashView.setText("CASH: $" + Integer.toString(cash));
        setWinningHand();

        /**
         * Set onClickListener for the No Double button.
         */
        Button noButton = (Button)findViewById(R.id.noButton);
        noButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        /**
         * Set onClickListener for the Yes Double button.
         */
        final Button yesButton = (Button)findViewById(R.id.yesButton);
        yesButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                yesButton.setVisibility(View.INVISIBLE);
            }
        });
    }
}
