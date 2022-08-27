package com.coearners.android.abacusview;

import android.content.Context;
import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.RelativeLayout;

import androidx.core.content.ContextCompat;

public class RodView extends RelativeLayout {
    final int[] earthBeadViews;
    MediaPlayer mediaPlayer;
    public static final int TRANSITION_SPEED = 150;
    private int unit;
    private float beamLocY;
    private int margin;
    private int value;
    private final Context context;
    private final int[] colors = {
            R.drawable.ic_bead_white,
            R.drawable.ic_bead_blue,
            R.drawable.ic_bead_alice_blue,
            R.drawable.ic_bead_red,
            R.drawable.ic_bead_green,
            R.drawable.ic_bead_yellow
    };
    HeavenBeadView heavenBeadView;
    public RodView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        this.mediaPlayer = MediaPlayer.create(context, R.raw.bead_move);
        TypedArray a = context.getTheme().obtainStyledAttributes(attrs,
                R.styleable.RodView, 0, 0);
        int color;
        try{
            unit = a.getInt(R.styleable.RodView_unit, 0);
            color = a.getInt(R.styleable.RodView_rodColor, 0);
        }finally {
            a.recycle();
        }
        this.value = 0;
        LayoutInflater inflater = (LayoutInflater) context
                .getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        inflater.inflate(R.layout.layout_rod, this, true);
        earthBeadViews = new int[]{
                R.id.firstBead,
                R.id.secondBead,
                R.id.thirdBead,
                R.id.fourthBead
        };
        this.setColor(color);
        heavenBeadView = findViewById(R.id.heavenBead);
    }

    public static void moveToY(View view, float locY){
        float delY = -1 * (view.getY() - locY);
        view.animate().translationYBy(delY).setDuration(TRANSITION_SPEED).start();
    }

    public void initialize(float beamLocY, int margin){
        this.beamLocY = beamLocY;
        this.margin = margin;
        heavenBeadView.setOnClickListener(v -> {
            mediaPlayer.start();
            if(heavenBeadView.getTouchingBeam()){
                heavenBeadView.scrollToTop(beamLocY, margin);
                this.value -= 5;
            }else{
                heavenBeadView.scrollToBeam(beamLocY, margin);
                this.value += 5;
            }
        });
        for(int i = 0; i < earthBeadViews.length; i++){
            EarthBeadView currentBead = this.findViewById(earthBeadViews[i]);
            int finalI = i;
            currentBead.setOnClickListener(v -> {
                mediaPlayer.start();
                if(currentBead.getTouchingBeam()){
                    for(int j = -1; j < finalI; j++){
                        EarthBeadView nextBead = findViewById(earthBeadViews[j+1]);
                        if(!nextBead.getTouchingBeam()){
                            continue;
                        }
                        float viewLocY;
                        viewLocY = nextBead.getY() + margin + beamLocY - nextBead.getHeight()*2 + nextBead.getHeight()/6f;
                        moveToY(nextBead, viewLocY);
                        nextBead.setTouchingBeam(false);
                        this.value -= 1;
                    }
                }else{

                    for(int j = earthBeadViews.length - 1; j > finalI - 1; j--){
                        EarthBeadView nextBead = findViewById(earthBeadViews[j]);
                        if(nextBead.getTouchingBeam()){
                            continue;
                        }
                        float viewLocY;
                        viewLocY = nextBead.getY() - margin - beamLocY + nextBead.getHeight()*2 - nextBead.getHeight()/6f;
                        moveToY(nextBead, viewLocY);
                        nextBead.setTouchingBeam(true);
                        this.value += 1;
                    }
                }
            });
        }
    }

    public void reset(){
        mediaPlayer.start();
        if(heavenBeadView.getTouchingBeam()){
            heavenBeadView.scrollToTop(beamLocY, margin);
            heavenBeadView.setTouchingBeam(false);
        }
        for (int earthBeadView : earthBeadViews) {
            EarthBeadView nextBead = findViewById(earthBeadView);
            if (!nextBead.getTouchingBeam()) {
                continue;
            }
            float viewLocY;
            viewLocY = nextBead.getY() + margin + beamLocY - nextBead.getHeight() * 2 + nextBead.getHeight() / 6f;
            moveToY(nextBead, viewLocY);
            nextBead.setTouchingBeam(false);
        }
        this.value = 0;
    }

    public int getUnit() {
        return unit;
    }

    public void setUnit(int unit) {
        this.unit = unit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public void setColor(int color){
        ((HeavenBeadView)findViewById(R.id.heavenBead)).setImageDrawable(ContextCompat.getDrawable(context, colors[color]));
        for(int beadId: earthBeadViews){
            ((EarthBeadView)findViewById(beadId)).setImageDrawable(ContextCompat.getDrawable(context, colors[color]));
        }
    }
}
