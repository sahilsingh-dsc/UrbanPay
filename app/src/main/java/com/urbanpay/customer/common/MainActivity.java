package com.urbanpay.customer.common;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.urbanpay.customer.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        initView();
    }

    private void initView() {
    }
}