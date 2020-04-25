package com.example.animdemo;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

public class PageTurnView extends View {
    private Paint paint;
    private Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.income);
    private Camera camera = new Camera();
    private int degree3DY1 = 0;
    private int degreeZ = 0;
    private int degree3DY2 = 0;
    private ObjectAnimator animStep1 = ObjectAnimator.ofInt(this, "degree3DY1", 0, -45);
    private ObjectAnimator animStep2 = ObjectAnimator.ofInt(this, "degreeZ", 0, 270);
    private ObjectAnimator animStep3 = ObjectAnimator.ofInt(this, "degree3DY2", 0, 45);
    private AnimatorSet animatorSet = new AnimatorSet();

    public PageTurnView(Context context) {
        super(context);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public PageTurnView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    public PageTurnView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    }

    {
        animStep1.setDuration(1500);
        animStep2.setDuration(1500);
        animStep3.setDuration(1500);
    }

    @Override
    protected void onAttachedToWindow() {
        super.onAttachedToWindow();
        animatorSet.playSequentially(animStep1, animStep2, animStep3);
        animatorSet.start();
        animatorSet.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                super.onAnimationEnd(animation);
                postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        reset();
                        animatorSet.start();
                    }
                }, 500);
            }
        });
    }

    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        animatorSet.end();
    }

    public void setDegreeZ(int degreeZ) {
        this.degreeZ = degreeZ;
        invalidate();
    }

    public void setDegree3DY1(int degreeY) {
        this.degree3DY1 = degreeY;
        invalidate();
    }

    public void setDegree3DY2(int degreeY) {
        this.degree3DY2 = degreeY;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int bmpWidth = bitmap.getWidth();
        int bmpHeight = bitmap.getHeight();

        //将画布坐标系平移至View中心
        canvas.translate(getWidth() / 2, getHeight() / 2);


        //绘制初始位置右半边


        canvas.save();
        //1 对坐标系先2D旋转
        canvas.rotate(-degreeZ);
        //2 对坐标系进行3D绕Y轴旋转
        camera.save();
        camera.rotateY(degree3DY1);
        camera.applyToCanvas(canvas);
        //3 裁切
        canvas.clipRect(0, -bmpHeight, bmpWidth, bmpHeight);
        //4 恢复2D旋转
        canvas.rotate(degreeZ);
        //5 绘制图形
        canvas.drawBitmap(bitmap, -bmpWidth / 2, -bmpHeight / 2, paint);
        camera.restore();
        canvas.restore();

        //绘制初始位置左半边

        canvas.save();
        //1 对坐标系先2D旋转
        canvas.rotate(-degreeZ);
        //2 对坐标系进行3D绕Y轴旋转
        camera.save();
        camera.rotateY(degree3DY2);
        camera.applyToCanvas(canvas);
        //3 裁切
        canvas.clipRect(-bmpWidth, -bmpHeight, 0, bmpHeight);
        //4 恢复2D旋转
        canvas.rotate(degreeZ);
        //5 绘制图形
        canvas.drawBitmap(bitmap, -bmpWidth / 2, -bmpHeight / 2, paint);
        camera.restore();
        canvas.restore();

    }

    private void reset() {
        degree3DY1 = 0;
        degreeZ = 0;
        degree3DY2 = 0;
    }
}
