package com.dicoding.picodiploma.myunittest;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText edtWidth, edtHeight, edtLength;
    private TextView tvResult;
    private MainViewModel mainViewModel;

    private Button btnCalculateVolume;
    private Button btnCalculateSurfaceArea;
    private Button btnCalculateCircumference;
    private Button btnSave;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mainViewModel = new MainViewModel(new CuboidModel());

        edtWidth = findViewById(R.id.edt_width);
        edtHeight = findViewById(R.id.edt_height);
        edtLength = findViewById(R.id.edt_length);
        tvResult = findViewById(R.id.tv_result);
        btnCalculateVolume = findViewById(R.id.btn_calculate_volume);
        btnCalculateCircumference = findViewById(R.id.btn_calculate_circumference);
        btnCalculateSurfaceArea = findViewById(R.id.btn_calculate_surface_area);
        btnSave = findViewById(R.id.btn_save);

        btnSave.setOnClickListener(this);
        btnCalculateSurfaceArea.setOnClickListener(this);
        btnCalculateCircumference.setOnClickListener(this);
        btnCalculateVolume.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        String length = edtLength.getText().toString().trim();
        String width = edtWidth.getText().toString().trim();
        String height = edtHeight.getText().toString().trim();

        if (TextUtils.isEmpty(length)) {
            edtLength.setError("Field ini tidak boleh kosong");
        } else if (TextUtils.isEmpty(width)) {
            edtWidth.setError("Field ini tidak boleh kosong");
        } else if (TextUtils.isEmpty(height)) {
            edtHeight.setError("Field ini tidak boleh kosong");
        } else {
            double l = Double.parseDouble(length);
            double w = Double.parseDouble(width);
            double h = Double.parseDouble(height);

            if (v.getId() == R.id.btn_save) {
                mainViewModel.save(l, w, h);
                visible();
            } else if (v.getId() == R.id.btn_calculate_circumference) {
                tvResult.setText(String.valueOf(mainViewModel.getCircumference()));
                gone();
            } else if (v.getId() == R.id.btn_calculate_surface_area) {
                tvResult.setText(String.valueOf(mainViewModel.getSurfaceArea()));
                gone();
            } else if (v.getId() == R.id.btn_calculate_volume) {
                tvResult.setText(String.valueOf(mainViewModel.getVolume()));
                gone();
            }
        }
    }

    private void visible() {
        btnCalculateVolume.setVisibility(View.VISIBLE);
        btnCalculateCircumference.setVisibility(View.VISIBLE);
        btnCalculateSurfaceArea.setVisibility(View.VISIBLE);
        btnSave.setVisibility(View.GONE);
    }

    private void gone() {
        btnCalculateVolume.setVisibility(View.GONE);
        btnCalculateCircumference.setVisibility(View.GONE);
        btnCalculateSurfaceArea.setVisibility(View.GONE);
        btnSave.setVisibility(View.VISIBLE);
    }
}
