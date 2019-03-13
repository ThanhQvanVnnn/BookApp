package com.phungthanhquan.bookapp.View.Activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.app.ActivityOptions;
import android.content.Intent;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.airbnb.lottie.LottieAnimationView;
import com.phungthanhquan.bookapp.R;

public class PlashScreen extends AppCompatActivity {

    private ImageView logo;
    private LottieAnimationView lottieAnimationView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plash_screen);
        lottieAnimationView = findViewById(R.id.animation);
        logo = findViewById(R.id.logo);
        function();

        Animation animation = AnimationUtils.loadAnimation(this,R.anim.plashscreen);
        Animation animation2 = AnimationUtils.loadAnimation(this,R.anim.plashscreen2);
        logo.startAnimation(animation);
        lottieAnimationView.setAnimation(animation2);
        Thread timer = new Thread(){
            public void run(){
                try {
                    sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                finally {
                    PlashScreen.this.runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Intent intent = new Intent(PlashScreen.this,Login.class);
                            ActivityOptions options = null;
                            if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                                options = (ActivityOptions) ActivityOptions.makeSceneTransitionAnimation(PlashScreen.this,
                                        logo,"vanchuyenlogo");
                            }
                            startActivity(intent, options.toBundle());
                        }
                    });

                }
            }
       };
        timer.start();

    }
    private void function(){
        ValueAnimator animator = ValueAnimator.ofFloat(0f,1f).setDuration(2500);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
            lottieAnimationView.setProgress((Float) animation.getAnimatedValue());
            }
        });
        if(lottieAnimationView.getProgress()==0f){

            animator.start();
        }else {
            lottieAnimationView.setProgress(0f);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
