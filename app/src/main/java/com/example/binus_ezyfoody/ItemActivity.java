package com.example.binus_ezyfoody;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.binus_ezyfoody.adapter.ListItemAdapter;

public class ItemActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_item);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        String type = "";

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            type = extras.getString("itemType");
        }

        RecyclerView rv = findViewById(R.id.my_recycler_view_item);
        rv.setHasFixedSize(true);

        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        rv.setLayoutManager(layoutManager);

        RecyclerView.Adapter mAdapter = new ListItemAdapter(type, this);
        rv.setAdapter(mAdapter);

        TextView title = findViewById(R.id.textView_ItemCategory);
        title.setText(type);

        Button orderBtn = findViewById(R.id.button_cartOrderItem);
        orderBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(ItemActivity.this, OrderActivity.class);
                startActivity(i);
            }
        });

    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
}
