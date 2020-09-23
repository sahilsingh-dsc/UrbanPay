package com.urbanpay.customer.auth;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.tylersuehr.socialtextview.SocialTextView;
import com.urbanpay.customer.R;

import java.util.Objects;

public class PhoneActivity extends AppCompatActivity implements SocialTextView.OnLinkClickListener, View.OnClickListener {

    private SocialTextView stvTerms;
    private EditText etPhoneNumber;
    private TextView tvContinue;

    private static final String TAG = "PhoneActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_phone);
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
        initView();
    }

    private void initView() {
        stvTerms = findViewById(R.id.stvTerms);
        stvTerms.setOnLinkClickListener(this);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        tvContinue = findViewById(R.id.tvContinue);
        tvContinue.setOnClickListener(this);

        setupUI(findViewById(R.id.framePhone));
    }

    @Override
    public void onLinkClicked(int i, String s) {
        Intent urlIntent = new Intent(Intent.ACTION_VIEW);
        urlIntent.setData(Uri.parse(s));
        startActivity(urlIntent);
    }

    public static void hideSoftKeyboard(Activity activity) {
        InputMethodManager inputMethodManager =
                (InputMethodManager) activity.getSystemService(
                        Activity.INPUT_METHOD_SERVICE);
        inputMethodManager.hideSoftInputFromWindow(
                Objects.requireNonNull(activity.getCurrentFocus()).getWindowToken(), 0);
    }

    public void setupUI(View view) {
        if (!(view instanceof EditText)) {
            view.setOnTouchListener(new View.OnTouchListener() {
                public boolean onTouch(View v, MotionEvent event) {
                    hideSoftKeyboard(PhoneActivity.this);
                    return false;
                }
            });
        }

        if (view instanceof ViewGroup) {
            for (int i = 0; i < ((ViewGroup) view).getChildCount(); i++) {
                View innerView = ((ViewGroup) view).getChildAt(i);
                setupUI(innerView);
            }
        }
    }

    @Override
    public void onClick(View view) {
        if (view == tvContinue) {
            String phoneNumber = etPhoneNumber.getText().toString();
            validatePhoneNumber(phoneNumber);
        }
    }

    private void validatePhoneNumber(String phoneNumber) {
        if (!TextUtils.isDigitsOnly(phoneNumber)){
            etPhoneNumber.setError("Enter valid phone number.");
            return;
        }
        if (TextUtils.isEmpty(phoneNumber)) {
            etPhoneNumber.setError("Phone number required.");
            return;
        }
        if (phoneNumber.length() != 10) {
            etPhoneNumber.setError("Enter a valid 10 digit phone number.");
            return;
        }

        gotoCodeScreen(phoneNumber);

    }

    private void gotoCodeScreen(String phoneNumber) {
        Intent intent = new Intent(PhoneActivity.this, CodeActivity.class);
        intent.putExtra("phoneNumber", phoneNumber);
        startActivity(intent);
    }
}