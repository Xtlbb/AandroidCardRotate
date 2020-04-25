package com.example.animdemo;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.animation.AnimatorInflater;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView imageviewone;
    ImageView imageviewtwo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        imageviewone = findViewById(R.id.imageviewone);
        imageviewtwo= findViewById(R.id.imageviewtwo);

        imageviewone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ActTwo.class);
                startActivity(intent);
            }
        });

        imageviewtwo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThreeAct.class);
                startActivity(intent);
            }
        });


//        show1();
//        @SuppressLint("ResourceType") AnimatorSet inAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.anim.rotate_in_anim);
//        @SuppressLint("ResourceType") AnimatorSet outAnimator = (AnimatorSet) AnimatorInflater.loadAnimator(this, R.anim.rotate_out_anim);
//        int distance = 16000;
//        float scale = getResources().getDisplayMetrics().density * distance;
//        imageviewone.setCameraDistance(scale); //设置镜头距离
//        imageviewone.setCameraDistance(scale); //设置镜头距离
//        outAnimator.setTarget(imageviewone);
//        inAnimator.setTarget(imageviewone);
//        outAnimator.start();
//        inAnimator.start();
//        outAnimator.addListener(new AnimatorListenerAdapter() {
//            @Override
//            public void onAnimationEnd(Animator animation) {
//                super.onAnimationEnd(animation);
//                imageviewone.setVisibility(View.GONE);
//                imageviewone.setAlpha(1.0f);
//                imageviewone.setRotationY(0.0f);
//                imageviewtwo.setVisibility(View.VISIBLE);
//            }
//        });

    }



    private void show1(){

        //  翻过去的动画
        ObjectAnimator mAnimatorA1 = ObjectAnimator.ofFloat(imageviewone, View.ROTATION_Y, 0, 90).setDuration(500);
        final ObjectAnimator   mAnimatorB1 = ObjectAnimator.ofFloat(imageviewtwo, View.ROTATION_Y, 90, 180).setDuration(500);

        mAnimatorA1.start();
        mAnimatorA1.setDuration(1000);
        mAnimatorA1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
//                mActivityMain.setClickable(false);
            }
            @Override
            public void onAnimationEnd(Animator animation) {
//                mActivityMain.setClickable(true);
                mAnimatorB1.start();
            }
        });
        mAnimatorB1.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationStart(Animator animation) {
//                mActivityMain.setClickable(false);
            }
            @Override
            public void onAnimationEnd(Animator animation) {
//                mActivityMain.setClickable(true);
            }
        });


    }
}



