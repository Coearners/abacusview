package com.coearners.android.abacusview;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.util.AttributeSet;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatImageView;

import java.io.Serializable;

public class EarthBeadView extends AppCompatImageView implements Serializable {
    private Boolean isTouchingBeam;
    private int beadValue;
    private int beadId;

    public EarthBeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.BeadView,
                0, 0);
        try {
            beadValue = a.getInteger(R.styleable.BeadView_beadValue, 0);
            beadId = a.getInteger(R.styleable.BeadView_beadId, 1);
        } finally {
            a.recycle();
        }
        isTouchingBeam = false;
    }

    private void scrollToBeam(float locY, Resources r){
        isTouchingBeam = true;
        this.setBeadValue(this.beadId);
    }

    private void scrollToTop(){
        isTouchingBeam = false;
        this.setBeadValue(0);
    }

    public void toggle(float locY, Resources r){
        if(this.isTouchingBeam){
            this.scrollToTop();
        }else{
            this.scrollToBeam(locY, r);
        }
    }

    public Boolean getTouchingBeam() {
        return isTouchingBeam;
    }

    public void setTouchingBeam(Boolean touchingBeam) {
        isTouchingBeam = touchingBeam;
    }

    public int getBeadValue() {
        return beadValue;
    }

    public void setBeadValue(int beadValue) {
        this.beadValue = beadValue;
    }

    public int getBeadId() {
        return beadId;
    }

    public void setBeadId(int beadId) {
        this.beadId = beadId;
    }
}
