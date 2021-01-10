package com.example.binus_ezyfoody;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binus_ezyfoody.EzFood.Drink;
import com.example.binus_ezyfoody.EzFood.Food;
import com.example.binus_ezyfoody.EzFood.Snack;
import com.example.binus_ezyfoody.Singleton.SingleInstance;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        SharedPreferences sharedPreferences = getSharedPreferences("ezfood", 0);
        SingleInstance.Funds = sharedPreferences.getInt("Funds", 0);

        initItem();

        Button orderBtn, drinkBtn, snackBtn, foodBtn, topupBtn, mapBtn;
        TextView Funds;

        orderBtn = findViewById(R.id.button_cartOrder);
        drinkBtn = findViewById(R.id.button_drink);
        snackBtn = findViewById(R.id.button_Snacks);
        foodBtn = findViewById(R.id.button_Foods);
        topupBtn = findViewById(R.id.button_TopUp);
        mapBtn = findViewById(R.id.button_Map);
        Funds = findViewById(R.id.textView_Funds);

        Funds.setText("Rp." + SingleInstance.Funds);

        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, OrderActivity.class);
                startActivity(i);
            }
        });

        drinkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ItemActivity.class);
                i.putExtra("itemType", "Drinks");
                startActivity(i);
            }
        });

        snackBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ItemActivity.class);
                i.putExtra("itemType", "Snacks");
                startActivity(i);
            }
        });

        foodBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, ItemActivity.class);
                i.putExtra("itemType", "Foods");
                startActivity(i);
            }
        });

        topupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, TopUpActivity.class);
                startActivity(i);
            }
        });

        mapBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, StoreMapsActivity.class);
                startActivity(i);
            }
        });

    }

    private void initItem() {
        Drink drink1 = new Drink("Air Mineral", 3000, 1);
        Drink drink2 = new Drink("Jus Apel", 13000, 1);
        Drink drink3 = new Drink("Jus Jeruk", 10000, 1);
        Drink drink4 = new Drink("Soft Drink", 5000, 1);

        SingleInstance.getInstanceDrink().add(drink1);
        SingleInstance.getInstanceDrink().add(drink2);
        SingleInstance.getInstanceDrink().add(drink3);
        SingleInstance.getInstanceDrink().add(drink4);


        Snack snack1 = new Snack("biskuit", 5000, 3);
        Snack snack2 = new Snack("Kerupuk", 1000, 3);

        SingleInstance.getInstanceSnack().add(snack1);
        SingleInstance.getInstanceSnack().add(snack2);


        Food food1 = new Food("Nasi Goreng", 23000, 2);
        Food food2 = new Food("Mie Goreng", 27000, 2);
        Food food3 = new Food("Ayam Goreng", 30000, 2);

        SingleInstance.getInstanceFood().add(food1);
        SingleInstance.getInstanceFood().add(food2);
        SingleInstance.getInstanceFood().add(food3);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

}
