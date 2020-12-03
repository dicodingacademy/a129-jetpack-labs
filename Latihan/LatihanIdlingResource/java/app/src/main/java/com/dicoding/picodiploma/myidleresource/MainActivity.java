package com.dicoding.picodiploma.myidleresource;

import android.os.Bundle;
import android.os.Handler;

import androidx.appcompat.app.AppCompatActivity;

import com.dicoding.picodiploma.myidleresource.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding activityMainBinding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activityMainBinding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(activityMainBinding.getRoot());

        activityMainBinding.button.setOnClickListener(v -> {
            delay1();
            delay2();
        });
    }

    private void delay1() {
        EspressoIdlingResource.increment();
        new Handler().postDelayed(() -> {
            activityMainBinding.textView.setText(getString(R.string.delay1));

            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                //Memberitahukan bahwa tugas sudah selesai dijalankan
                EspressoIdlingResource.decrement();
            }

        }, 2000);
    }

    private void delay2() {
        EspressoIdlingResource.increment();
        new Handler().postDelayed(() -> {
            activityMainBinding.textView.setText(getString(R.string.delay2));

            if (!EspressoIdlingResource.getEspressoIdlingResource().isIdleNow()) {
                //Memberitahukan bahwa tugas sudah selesai dijalankan
                EspressoIdlingResource.decrement();
            }
        }, 3000);
    }
}
