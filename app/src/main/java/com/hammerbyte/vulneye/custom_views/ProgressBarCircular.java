package com.hammerbyte.vulneye.custom_views;

import android.animation.ObjectAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.Nullable;

import com.hammerbyte.vulneye.R;


public class ProgressBarCircular extends View {

    //Few variables which will be required to draw the view
    private int colorFilledArc;
    private int colorUnFilledArc;
    private int colorCircularBackground;
    private int colorTextPrimary;
    private int colorTextSecondary;
    private int sizeTextPrimary;
    private int sizeTextSecondary;
    private int textDistance;
    private int widthUnFilledArc;
    private int widthFilledArc;
    private int centerOffset;
    //progress variables;
    private float progress = 0;
    //Object Animator
    private ObjectAnimator objectAnimator;
    //Progress Message
    private String progressMessage;
    //Runnable which will be executed at the 100%
    private Runnable onFinishRunnable;

    public ProgressBarCircular(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        configView(attrs);
    }

    public ProgressBarCircular(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        configView(attrs);
    }

    public Runnable getOnFinishRunnable() {
        return onFinishRunnable;
    }

    public void setOnFinishRunnable(Runnable onFinishRunnable) {
        this.onFinishRunnable = onFinishRunnable;
    }

    public ObjectAnimator getObjectAnimator() {
        return objectAnimator;
    }

    public void setObjectAnimator(ObjectAnimator objectAnimator) {
        this.objectAnimator = objectAnimator;
    }

    public int getWidthFilledArc() {
        return widthFilledArc;
    }

    public void setWidthFilledArc(int widthFilledArc) {
        this.widthFilledArc = widthFilledArc;
    }

    public int getColorFilledArc() {
        return colorFilledArc;
    }

    public void setColorFilledArc(int colorFilledArc) {
        this.colorFilledArc = colorFilledArc;
        postInvalidate();
    }

    public int getColorUnFilledArc() {
        return colorUnFilledArc;
    }

    public void setColorUnFilledArc(int colorUnFilledArc) {
        this.colorUnFilledArc = colorUnFilledArc;
        postInvalidate();
    }

    public int getColorCircularBackground() {
        return colorCircularBackground;
    }

    public void setColorCircularBackground(int colorCircularBackground) {
        this.colorCircularBackground = colorCircularBackground;
        postInvalidate();
    }

    public int getColorTextPrimary() {
        return colorTextPrimary;
    }

    public void setColorTextPrimary(int colorTextPrimary) {
        this.colorTextPrimary = colorTextPrimary;
        postInvalidate();
    }

    public int getColorTextSecondary() {
        return colorTextSecondary;
    }

    public void setColorTextSecondary(int colorTextSecondary) {
        this.colorTextSecondary = colorTextSecondary;
        postInvalidate();
    }

    public int getSizeTextPrimary() {
        return sizeTextPrimary;
    }

    public void setSizeTextPrimary(int sizeTextPrimary) {
        this.sizeTextPrimary = sizeTextPrimary;
        postInvalidate();
    }

    public int getSizeTextSecondary() {
        return sizeTextSecondary;
    }

    public void setSizeTextSecondary(int sizeTextSecondary) {
        this.sizeTextSecondary = sizeTextSecondary;
        postInvalidate();
    }

    public int getTextDistance() {
        return textDistance;
    }

    public void setTextDistance(int textDistance) {
        this.textDistance = textDistance;
        postInvalidate();
    }

    public int getWidthUnFilledArc() {
        return widthUnFilledArc;
    }

    public void setWidthUnFilledArc(int widthUnFilledArc) {
        this.widthUnFilledArc = widthUnFilledArc;
        postInvalidate();
    }

    public int getCenterOffset() {
        return centerOffset;
    }

    public void setCenterOffset(int centerOffset) {
        this.centerOffset = centerOffset;
        postInvalidate();
    }

    public float getProgress() {
        return progress;
    }


    //Set Progressbar without any effect
    public void setProgress(float progress) {
        this.progress = progress;
        postInvalidate();
        if (progress == 100 && getOnFinishRunnable() != null)
            getOnFinishRunnable().run();
    }


    public String getProgressMessage() {
        return progressMessage;
    }

    //sets the progress Message
    public void setProgressMessage(String progressMessage) {
        this.progressMessage = progressMessage;
        postInvalidate();
    }

    //View Derives User Given Attributes
    private void configView(AttributeSet attributeSet) {

        //Setting up User Passed arguments to the actual view set
        TypedArray attributeArray = getContext().getTheme().obtainStyledAttributes(attributeSet, R.styleable.AttributesCircularProgressBar, 0, 0);
        setColorFilledArc(attributeArray.getColor(R.styleable.AttributesCircularProgressBar_filledArcColor, getResources().getColor(R.color.orange500)));
        setColorUnFilledArc(attributeArray.getColor(R.styleable.AttributesCircularProgressBar_unFilledArcColor, getResources().getColor(R.color.colorDivider)));
        setColorCircularBackground(attributeArray.getColor(R.styleable.AttributesCircularProgressBar_circularBackgroundColor, getResources().getColor(R.color.blueGrey500)));
        setColorTextPrimary(attributeArray.getColor(R.styleable.AttributesCircularProgressBar_primaryTextColor, getResources().getColor(R.color.blueGrey950)));
        setColorTextSecondary(attributeArray.getColor(R.styleable.AttributesCircularProgressBar_secondaryTextColor, getResources().getColor(R.color.blueGrey950)));
        setSizeTextPrimary(attributeArray.getDimensionPixelSize(R.styleable.AttributesCircularProgressBar_primaryTextSize, 48));
        setSizeTextSecondary(attributeArray.getDimensionPixelSize(R.styleable.AttributesCircularProgressBar_secondaryTextSize, 16));
        setWidthUnFilledArc(attributeArray.getDimensionPixelSize(R.styleable.AttributesCircularProgressBar_unFilledArcWidth, 4));
        setWidthFilledArc(attributeArray.getDimensionPixelSize(R.styleable.AttributesCircularProgressBar_filledArcWidth, 4));
        setTextDistance(attributeArray.getDimensionPixelSize(R.styleable.AttributesCircularProgressBar_textDistance, 0));
        setCenterOffset(attributeArray.getDimensionPixelSize(R.styleable.AttributesCircularProgressBar_centerOffset, 0));
        setProgressMessage(attributeArray.getString(R.styleable.AttributesCircularProgressBar_secondaryText));

        //recycling array for reuse
        attributeArray.recycle();

        //initlization of object animator which smooths the progress
        setObjectAnimator(new ObjectAnimator());
    }


    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //Setting the Rectangle Size
        setMeasuredDimension(Math.min(widthMeasureSpec, heightMeasureSpec), Math.min(widthMeasureSpec, heightMeasureSpec));
    }


    //Called When view is Attached to The Window
    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        //Initially Showing some fade in effect
        viewLoadAnimation();
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        //Load the animation of fadeout when it not longer required
        viewDestroyAnimation();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        //Final Rectangle Sizing
        RectF viewRect = new RectF(getPaddingLeft(), getPaddingTop(), getMeasuredWidth() - getPaddingRight(), getMeasuredHeight() - getPaddingBottom());

        //Brushes For Text and Arc Drawing
        Paint Brush = new Paint();

        //Drawing The background
        Brush.setColor(getColorCircularBackground());
        Brush.setStyle(Paint.Style.FILL);
        Brush.setStrokeWidth(0.0f);
        canvas.drawArc(viewRect.left + getWidthUnFilledArc(), viewRect.top + getWidthUnFilledArc(), viewRect.right - getWidthUnFilledArc(), viewRect.bottom - getWidthUnFilledArc(), 0.0f, 360.0f, false, Brush);


        //TextBrush Initiating
        TextPaint TextBrush = new TextPaint();
        TextBrush.setAntiAlias(true);
        TextBrush.setTypeface(Typeface.create("sans-serif-light", Typeface.NORMAL));
        TextBrush.setTextAlign(Paint.Align.CENTER);

        //Drawing Primary Text
        TextBrush.setColor(getColorTextPrimary());
        TextBrush.setTextSize(getSizeTextPrimary());
        canvas.drawText((int) getProgress() + "%", getWidth() / 2, getCenterOffset() + (getHeight() / 2), TextBrush);

        //Drawing Secondary Text
        TextBrush.setColor(getColorTextSecondary());
        TextBrush.setTextSize(getSizeTextSecondary());
        canvas.drawText(getProgressMessage(), getWidth() / 2, getCenterOffset() + (getHeight() / 2) + (getSizeTextPrimary() / 2) + getTextDistance(), TextBrush);


        //Initiating Brush 
        Brush.setStyle(Paint.Style.STROKE);
        Brush.setFlags(Paint.ANTI_ALIAS_FLAG);
        Brush.setShadowLayer(15, 0, 0, Color.BLACK);

        //Drawing UnFilled Arc
        Brush.setStrokeWidth(getWidthUnFilledArc());
        Brush.setColor(getColorUnFilledArc());
        canvas.drawArc(viewRect.left + getWidthUnFilledArc(), viewRect.top + getWidthUnFilledArc(), viewRect.right - getWidthUnFilledArc(), viewRect.bottom - getWidthUnFilledArc(), 0, 360, false, Brush);

        //Drawing Primary Arc
        Brush.setStrokeWidth(getWidthFilledArc());
        Brush.setColor(getColorFilledArc());
        canvas.drawArc(viewRect.left + getWidthUnFilledArc(), viewRect.top + getWidthUnFilledArc(), viewRect.right - getWidthUnFilledArc(), viewRect.bottom - getWidthUnFilledArc(), 90.0f, (float) (getProgress() * 3.6), false, Brush);

    }

    //Animation Which can Show Some Fade In Effect !
    private void viewLoadAnimation() {
        startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_bounce));
    }

    //Animation Which can Show Some Fade Out Effect !
    private void viewDestroyAnimation() {
        startAnimation(AnimationUtils.loadAnimation(getContext(), R.anim.anim_fadeout));
    }

    //Moves the Variable called "Progress" from its current value to passed value
    public void setProgressWithAnimation(float progress) {
        //if priviously animator is working then stop it !!!
        if (getObjectAnimator().isRunning())
            getObjectAnimator().cancel();
        setObjectAnimator(ObjectAnimator.ofFloat(this, "progress", progress).setDuration(1000));
        getObjectAnimator().setInterpolator(new DecelerateInterpolator());
        getObjectAnimator().start();
    }


}
