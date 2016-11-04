package com.vdthai.vdeopoker;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by vdthai on 2016-10-30.
 */

/**
 * Custom adapter class for the winning hands.
 * Using view-holder pattern for adapters.
 */
class ScoreViewAdapter extends BaseAdapter {
    private List<ScoreBoard> handScores;
    private Activity activity;

    ScoreViewAdapter( Activity _activity, List<ScoreBoard> _scores ){
        super();
        handScores = _scores;
        activity = _activity;
    }

    @Override
    public int getCount(){
        return handScores.size();
    }

    @Override
    public Object getItem( int _position ){
        return handScores.get( _position );
    }

    @Override
    public long getItemId( int _position ){
        return _position;
    }

    private class ViewHolder{
        TextView handText;
        TextView betOneText;
        TextView betTwoText;
        TextView betThreeText;
        TextView betFourText;
    }

    @Override
    public View getView( int _position, View _convertView, ViewGroup _parent ){
        ViewHolder holder;
        LayoutInflater inflater = activity.getLayoutInflater();

        if( _convertView == null ){
            _convertView = inflater.inflate( R.layout.scoreview, null );
            holder = new ViewHolder();
            holder.handText = (TextView)_convertView.findViewById( R.id.handText );
            holder.betOneText = (TextView)_convertView.findViewById( R.id.betOneText );
            holder.betTwoText = (TextView)_convertView.findViewById( R.id.betTwoText );
            holder.betThreeText = (TextView)_convertView.findViewById( R.id.betThreeText );
            holder.betFourText = (TextView)_convertView.findViewById( R.id.betFourText );
            _convertView.setTag( holder );
        } else {
            holder = (ViewHolder)_convertView.getTag();
        }

        ScoreBoard item = handScores.get( _position );
        holder.handText.setText( item.getHand() );
        holder.betOneText.setText( Integer.toString( item.getBetOne() ) );
        holder.betTwoText.setText( Integer.toString( item.getBetTwo() ) );
        holder.betThreeText.setText( Integer.toString( item.getBetThree() ) );
        holder.betFourText.setText( Integer.toString( item.getBetFour() ) );

        return _convertView;
    }
}
