package com.dicoding.picodiploma.myidleresource;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.text_view);

        findViewById(R.id.button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                delay1();
                delay2();
            }
        });
    }

    private void delay1() {
        EspressoIdlingResource.increment();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText(getString(R.string.delay1));

                if (!EspressoIdlingResource.getEspressoIdlingResourcey().isIdleNow()) {
                    //Memberitahukan bahwa tugas sudah selesai dijalankan
                    EspressoIdlingResource.decrement();
                }

            }
        }, 2000);
    }

    private void delay2() {
        EspressoIdlingResource.increment();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                textView.setText(getString(R.string.delay2));

                if (!EspressoIdlingResource.getEspressoIdlingResourcey().isIdleNow()) {
                    //Memberitahukan bahwa tugas sudah selesai dijalankan
                    EspressoIdlingResource.decrement();
                }
            }
        }, 3000);
    }
}
