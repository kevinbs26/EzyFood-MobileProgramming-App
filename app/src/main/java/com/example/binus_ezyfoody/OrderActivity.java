package com.example.binus_ezyfoody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.binus_ezyfoody.Singleton.SingleInstance;
import com.example.binus_ezyfoody.adapter.ListOrderAdapter;

public class OrderActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        calculateTotal();

        RecyclerView rv = findViewById(R.id.my_recycler_view_itemOrder);
        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        RecyclerView.Adapter mAdapter = new ListOrderAdapter( this);
        rv.setAdapter(mAdapter);

        TextView totalOrderPriceTxt = findViewById(R.id.textView_totalOrderPrice);
        totalOrderPriceTxt.setText("Total Order Price: Rp. " + SingleInstance.totalOrderPrice);

        Button payBtn = findViewById(R.id.button_PayOrder);
        payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(SingleInstance.Funds >= SingleInstance.totalOrderPrice){
                    SingleInstance.Funds -= SingleInstance.totalOrderPrice;
                    SharedPreferences sharedPreferences = getSharedPreferences("ezfood", 0);
                    sharedPreferences.edit().putInt("Funds", SingleInstance.Funds).apply();

                    SingleInstance.getInstanceCart().clear();
                    SingleInstance.totalOrderPrice = 0;
                    Toast.makeText(OrderActivity.this, "Order Completed", Toast.LENGTH_LONG).show();

                    Intent i = new Intent(OrderActivity.this, MainActivity.class);
                    startActivity(i);
                    finish();
                }
                else{
                    Toast.makeText(OrderActivity.this, "Order Failed", Toast.LENGTH_LONG).show();
                }

            }
        });

    }

    public void calculateTotal(){
        SingleInstance.totalOrderPrice = 0;
        for(int i = 0; i < SingleInstance.getInstanceCart().size() ; i++){
            SingleInstance.totalOrderPrice += SingleInstance.getInstanceCart().get(i).getPrice() * SingleInstance.getInstanceCart().get(i).getQuantity();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
