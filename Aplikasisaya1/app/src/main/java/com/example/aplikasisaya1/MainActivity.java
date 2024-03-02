package com.example.aplikasisaya1;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText etLength, etWidth, etHeigh;
    private Button btnVolume, btnLuas, btnKeliling;
    private TextView tvResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etLength = findViewById(R.id.etLength);
        etWidth = findViewById(R.id.etWidth);
        etHeigh = findViewById(R.id.etHeigh);
        btnVolume = findViewById(R.id.btnVolume);
        btnLuas = findViewById(R.id.btnLuas);
        btnKeliling = findViewById(R.id.btnKeliling);
        tvResult = findViewById(R.id.tvResult);

        btnVolume.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateVolume();
            }
        });

        btnLuas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateLuas();
            }
        });

        btnKeliling.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateKeliling();
            }
        });
    }

    private void calculateVolume() {
        try {
            double length = Double.parseDouble(etLength.getText().toString());
            double width = Double.parseDouble(etWidth.getText().toString());
            double heigh = Double.parseDouble(etHeigh.getText().toString());

            double volume = length * width * heigh;

            tvResult.setText("Volume: " + volume);
        } catch (NumberFormatException e) {
            tvResult.setText("Harap Masukkan Inputan yang benar");
        }
    }

    private void calculateLuas() {
        try {
            double length = Double.parseDouble(etLength.getText().toString());
            double width = Double.parseDouble(etWidth.getText().toString());
            double heigh = Double.parseDouble(etHeigh.getText().toString());

            double luas = 2 * (length * width + width * heigh + length * heigh);

            tvResult.setText("Luas: " + luas);
        } catch (NumberFormatException e) {
            tvResult.setText("Please enter valid dimensions.");
        }
    }

    private void calculateKeliling() {
        try {
            double length = Double.parseDouble(etLength.getText().toString());
            double width = Double.parseDouble(etWidth.getText().toString());
            double weight = Double.parseDouble(etHeigh.getText().toString());

            double keliling = 4 * (length + width + weight);

            tvResult.setText("Keliling: " + keliling);
        } catch (NumberFormatException e) {
            tvResult.setText("Please enter valid dimensions.");
        }
    }
}