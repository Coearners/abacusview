package com.coearners.android.abacusview;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;

import java.io.Serializable;

public class HeavenBeadView extends androidx.appcompat.widget.AppCompatImageView implements Serializable {
    private Boolean isTouchingBeam;
    private int beadValue;
    private int beadId;
    private int topMargin = 0;

    public HeavenBeadView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        TypedArray a = context.getTheme().obtainStyledAttributes(
                attrs,
                R.styleable.BeadView,
                0, 0);
        try {
            beadValue = a.getInteger(R.styleable.BeadView_beadValue, 0);
            beadId = a.getInteger(R.styleable.BeadView_beadId, 5);
        } finally {
            a.recycle();
        }
        isTouchingBeam = false;
    }

    private RelativeLayout.LayoutParams layoutParams(){
        return (RelativeLayout.LayoutParams) this.getLayoutParams();
    }

    private void addLayoutRule(int verb){
        RelativeLayout.LayoutParams lp = this.layoutParams();
        lp.addRule(verb);
        this.setLayoutParams(lp);
    }

    private void addLayoutRule(int verb, int subject){
        RelativeLayout.LayoutParams lp = this.layoutParams();
        lp.addRule(verb, subject);
        this.setLayoutParams(lp);
    }

    private void removeLayoutRule(int verb){
        RelativeLayout.LayoutParams lp = this.layoutParams();
        lp.removeRule(verb);
        this.setLayoutParams(lp);
    }

    public void scrollToBeam(float locY, int margin){
        this.removeLayoutRule(RelativeLayout.ALIGN_PARENT_TOP);
        float viewLocY = locY + margin - this.getY() - topMargin - this.getHeight() - this.getHeight()/6f;
        isTouchingBeam = true;
        beadValue = 5;
        RodView.moveToY(this, viewLocY);

    }

    public void scrollToTop(float locY, int margin){
        float viewLocY = -locY - margin + this.getY() + topMargin + this.getHeight() + this.getHeight()/6f;
        RodView.moveToY(this, -1*viewLocY);
        isTouchingBeam = false;
        beadValue = 0;
    }

    @Deprecated
    public void toggle(float locY, int margin){

        if(this.topMargin == 0f){
            this.topMargin = this.layoutParams().topMargin;
        }
        if(this.isTouchingBeam){
            this.scrollToTop(locY, margin);
        }else{
            this.scrollToBeam(locY, margin);
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
