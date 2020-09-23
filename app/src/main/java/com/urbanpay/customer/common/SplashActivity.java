package com.urbanpay.customer.common;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.urbanpay.customer.R;
import com.urbanpay.customer.auth.PhoneActivity;

public class SplashActivity extends AppCompatActivity {

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        initView();
    }

    private void initView() {
        ImageView ivLogo = findViewById(R.id.ivLogo);
        Glide.with(SplashActivity.this).load(R.drawable.urbanpay_full_logo).into(ivLogo);
        firebaseAuth = FirebaseAuth.getInstance();
        delay3000milli();
    }

    private void delay3000milli() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (firebaseAuth.getCurrentUser() != null) {
                    gotoMain();
                } else {
                    gotoLogin();
                }
            }
        }, 3000);
    }

    private void gotoLogin() {
        Intent intent = new Intent(SplashActivity.this, PhoneActivity.class);
        startActivity(intent);
        finish();
    }

    private void gotoMain() {
        Intent intent = new Intent(SplashActivity.this, MainActivity.class);
        startActivity(intent);
        finish();
    }
}