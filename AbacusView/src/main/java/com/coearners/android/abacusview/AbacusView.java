package com.coearners.android.abacusview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.GridLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class AbacusView extends RelativeLayout implements Serializable {
    GridLayout layoutRods;

    public AbacusView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray a = context.obtainStyledAttributes(attrs,
                R.styleable.AbacusView, 0, 0);

        a.recycle();
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_abacus_view, this, true);
        View beamView = findViewById(R.id.beam);
        float beamLocY = beamView.getY();
        layoutRods = findViewById(R.id.layoutRods);
        int rods = layoutRods.getChildCount();
        int unitRange = rods - 4;
        int[] beadColors = {
                BeadColor.GREEN,
                BeadColor.ALICE_BLUE,
                BeadColor.YELLOW,
                BeadColor.RED,
                BeadColor.BLUE
        };
        int beadIndex = 0;
        for(int i = 0; i < rods; i++){
            View v = layoutRods.getChildAt(i);
            if(v instanceof RodView){
                RodView rodView = (RodView)v;
//                if(i == 5){
//                    rodView.setColor(BeadColor.WHITE);
//                }else{
//                    if(i >= beadColors.length){
//                        beadIndex = 0;
//                    }
//                    rodView.setColor(beadColors[beadIndex]);
//                }
                rodView.setUnit(unitRange);
                rodView.setValue(0);
                rodView.initialize(beamLocY, ((LayoutParams)beamView.getLayoutParams()).topMargin);
                unitRange--;
                beadIndex++;
            }
        }
    }



    public void reset(){
        for(int i = 0; i < layoutRods.getChildCount(); i++){
            View v = layoutRods.getChildAt(i);
            if(v instanceof RodView){
                ((RodView)v).reset();
            }
        }
    }

    public double getValue() {
        double value = 0.000f;
        for(int i = 0; i < layoutRods.getChildCount(); i++){
            View v = layoutRods.getChildAt(i);
            if(v instanceof RodView){
                RodView rod = (RodView) v;
                double rodValue =  ((double)rod.getValue() * Math.pow(10, rod.getUnit()));
                value += rodValue;
            }
        }
        if(value % 1 != 0){
            value = (double)Math.round(value * Math.pow(10, 3)) / Math.pow(10, 3);
        }
        return value;
    }

    public String getStringValue(){
        double value = this.getValue();
        String strValue = String.valueOf(value);
        if(value % 1 == 0){
            strValue = String.valueOf((int)value);
        }
        return strValue;
    }
}
