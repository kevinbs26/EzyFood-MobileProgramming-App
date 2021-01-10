package com.example.binus_ezyfoody;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binus_ezyfoody.EzFood.EzFood;
import com.example.binus_ezyfoody.Singleton.SingleInstance;

public class ItemDetailActivity extends AppCompatActivity {

    Button minusBtn, plusBtn, addButton;
    TextView titleView, priceView, totalView, quantityView;
    EzFood obj = null;
    int quantity = 1;
    int totalPrice = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item_detail);

        String type = "";
        int pos = -1;

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getString("itemType");
            pos = extras.getInt("ItemArrayPosition");
        }

        if(type.equals("Drinks")){
            obj = SingleInstance.getInstanceDrink().get(pos);
        }
        else if (type.equals("Snacks")){
            obj = SingleInstance.getInstanceSnack().get(pos);
        }
        else if(type.equals("Foods")){
            obj = SingleInstance.getInstanceFood().get(pos);
        }

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        titleView = findViewById(R.id.textView_ItemDetailTitle);
        priceView = findViewById(R.id.textView_ItemDetailPrice);
        totalView = findViewById(R.id.textView_ItemDetailTotalPrice);
        quantityView = findViewById(R.id.textView_quantity);

        minusBtn = findViewById(R.id.button_minus);
        plusBtn = findViewById(R.id.button_plus);
        addButton = findViewById(R.id.button_AddOrder);


        titleView.setText(obj.getName());
        priceView.setText("Rp. " + obj.getPrice());
        quantityView.setText(quantity + "");
        totalPrice = calculate();
        totalView.setText("Total Price: " + "Rp. " + totalPrice);

        minusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantity == 1) return;
                quantity--;
                quantityView.setText(quantity + "");
                totalPrice = calculate();
                totalView.setText("Total Price: " + "Rp. " + totalPrice);
            }
        });
        plusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(quantity == 99) return;
                quantity++;
                quantityView.setText(quantity + "");
                totalPrice = calculate();
                totalView.setText("Total Price: " + "Rp. " + totalPrice);
            }
        });

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                obj.setQuantity(quantity);
                SingleInstance.getInstanceCart().add(obj);
                Toast.makeText(ItemDetailActivity.this, obj.getName() + " has been added to your order", Toast.LENGTH_SHORT).show();
                onBackPressed();
            }
        });
    }

    private int calculate(){
        return obj.getPrice()*quantity;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
