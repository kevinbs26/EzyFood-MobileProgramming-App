package com.example.binus_ezyfoody;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.binus_ezyfoody.Singleton.SingleInstance;

public class TopUpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top_up);


        final EditText inputFunds = findViewById(R.id.editText_TopUp);
        Button buttonFunds = findViewById(R.id.button_AddFunds);

        buttonFunds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fundsString = String.valueOf(inputFunds.getText());
                int funds = Integer.parseInt(fundsString);
                SingleInstance.Funds += funds;

                Toast.makeText(TopUpActivity.this, "Top-Up Rp." + funds + " Successfully", Toast.LENGTH_SHORT).show();

                SharedPreferences sharedPreferences = getSharedPreferences("ezfood", 0);
                sharedPreferences.edit().putInt("Funds", SingleInstance.Funds).apply();

                Intent i = new Intent(TopUpActivity.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });
    }
}
