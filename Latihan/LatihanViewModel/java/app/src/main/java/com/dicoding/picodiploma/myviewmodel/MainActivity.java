package com.dicoding.picodiploma.myviewmodel;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

public class MainActivity extends AppCompatActivity {

    // Membuat varible global
    private EditText edtWidth, edtHeight, edtLength;
    private TextView tvResult;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Menghubungkan variable editText dan textView dengan layout
        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        tvResult = findViewById(R.id.tv_result);

        // Menghubungkan ViewModel dengan Activity
        viewModel = new ViewModelProvider(this).get(MainViewModel.class);

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