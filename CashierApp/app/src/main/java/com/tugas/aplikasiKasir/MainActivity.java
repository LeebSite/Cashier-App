import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private TextView tvTotalPrice;
    private EditText etMenu1Quantity, etMenu2Quantity, etMenu3Quantity, etMenu4Quantity;
    private RadioGroup radioGroup;
    private RadioButton radioButtonGold, radioButtonSilver, radioButtonPlatinum, radioButtonNone;
    private Button btnBeli, btnReset;

    private int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvTotalPrice = findViewById(R.id.tvTotalPrice);
        etMenu1Quantity = findViewById(R.id.etMenu1Quantity);
        etMenu2Quantity = findViewById(R.id.etMenu2Quantity);
        etMenu3Quantity = findViewById(R.id.etMenu3Quantity);
        etMenu4Quantity = findViewById(R.id.etMenu4Quantity);
        radioGroup = findViewById(R.id.radioGroup);
        radioButtonGold = findViewById(R.id.radioButtonGold);
        radioButtonSilver = findViewById(R.id.radioButtonSilver);
        radioButtonPlatinum = findViewById(R.id.radioButtonPlatinum);
        radioButtonNone = findViewById(R.id.radioButtonNone);
        btnBeli = findViewById(R.id.btnBeli);
        btnReset = findViewById(R.id.btnReset);

        btnBeli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Menghitung total harga
                calculateTotalPrice();

                // Menampilkan hasil belanja
                showPurchaseSummary();
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                resetData();
            }
        });
    }

    private void calculateTotalPrice() {
        int menu1Price = Integer.parseInt(etMenu1Quantity.getText().toString()) * 20000;
        int menu2Price = Integer.parseInt(etMenu2Quantity.getText().toString()) * 12000;
        int menu3Price = Integer.parseInt(etMenu3Quantity.getText().toString()) * 26000;
        int menu4Price = Integer.parseInt(etMenu4Quantity.getText().toString()) * 18000;

        totalPrice = menu1Price + menu2Price + menu3Price + menu4Price;

        // Menghitung diskon berdasarkan membership
        int discount = 0;
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radioButtonGold:
                discount = 10000;
                break;
            case R.id.radioButtonSilver:
                discount = 6000;
                break;
            case R.id.radioButtonPlatinum:
                discount = 3000;
                break;
            case R.id.radioButtonNone:
                discount = 0;
                break;
        }

        // Total harga setelah diskon
        totalPrice -= discount;

        // Diskon tambahan jika total harga melebihi 45000
        if (totalPrice > 45000) {
            totalPrice -= 8000;
        }

        // Update TextView total price
        tvTotalPrice.setText("Total Harga: " + totalPrice);
    }

    private void showPurchaseSummary() {
        // Menampilkan ringkasan pembelian
        String summary = "Anda telah membeli:\n";
        if (Integer.parseInt(etMenu1Quantity.getText().toString()) > 0) {
            summary += "- Cheese Burger x" + etMenu1Quantity.getText().toString() + "\n";
        }
        if (Integer.parseInt(etMenu2Quantity.getText().toString()) > 0) {
            summary += "- Lemon Cola x" + etMenu2Quantity.getText().toString() + "\n";
        }
        if (Integer.parseInt(etMenu3Quantity.getText().toString()) > 0) {
            summary += "- Kentucky x" + etMenu3Quantity.getText().toString() + "\n";
        }
        if (Integer.parseInt(etMenu4Quantity.getText().toString()) > 0) {
            summary += "- Potato Fries x" + etMenu4Quantity.getText().toString() + "\n";
        }

        summary += "Membership: ";
        switch (radioGroup.getCheckedRadioButtonId()) {
            case R.id.radioButtonGold:
                summary += "Gold\n";
                break;
            case R.id.radioButtonSilver:
                summary += "Silver\n";
                break;
            case R.id.radioButtonPlatinum:
                summary += "Platinum\n";
                break;
            case R.id.radioButtonNone:
                summary += "None\n";
                break;
        }

        summary += "Total Harga: " + totalPrice;

        Toast.makeText(this, summary, Toast.LENGTH_LONG).show();
    }

    private void resetData() {
        etMenu1Quantity.setText("");
        etMenu2Quantity.setText("");
        etMenu3Quantity.setText("");
        etMenu4Quantity.setText("");
        radioGroup.clearCheck();
        tvTotalPrice.setText("Total Harga: ");
    }
}
