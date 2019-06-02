package com.dicoding.picodiploma.myviewmodel;

import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    // Membuat varible global
    private EditText edtWidth, edtHeight, edtLength;
    private TextView tvResult;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menghubungkan ViewModel dengan Activity
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        // Menghubungkan variable editText dan textView dengan layout
        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        tvResult = findViewById(R.id.tv_result);

        // Menampilkan hasil pertama kali
        displayResult();

        // Memberikan aksi klik kepada button calculate
        findViewById(R.id.btn_calculate).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // Mendapatkan string dari editText
                String width = edtWidth.getText().toString();
                String height = edtHeight.getText().toString();
                String length = edtLength.getText().toString();

                // Melakukan pengecekan apakah empty atau tidak
                if (width.isEmpty()) {
                    edtWidth.setError("Masih kosong");
                } else if (height.isEmpty()) {
                    edtHeight.setError("Masih kosong");
                } else if (length.isEmpty()) {
                    edtLength.setError("Masih kosong");
                } else {

                    // Melakukan pengiriman string ke ViewModel
                    viewModel.calculate(width, height, length);

                    //Menampilkan hasil dari calculate
                    displayResult();
                }
            }
        });
    }

    // Menampilkan hasil dari ViewModel ke tvResults
    private void displayResult() {
        tvResult.setText(String.valueOf(viewModel.result));
    }
}