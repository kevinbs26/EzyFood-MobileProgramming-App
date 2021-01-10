package com.example.binus_ezyfoody.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.binus_ezyfoody.EzFood.EzFood;
import com.example.binus_ezyfoody.ItemDetailActivity;
import com.example.binus_ezyfoody.R;
import com.example.binus_ezyfoody.Singleton.SingleInstance;

import java.util.ArrayList;

public class ListItemAdapter extends RecyclerView.Adapter<ListItemAdapter.MyViewHolder> {
    ArrayList<EzFood> arr = null;
    Context mContext;
    String type = null;

    public ListItemAdapter(String type, Context context){
        if(type.equals("Drinks")){
            arr = SingleInstance.getInstanceDrink();
        }
        else if (type.equals("Snacks")){
            arr = SingleInstance.getInstanceSnack();
        }
        else if(type.equals("Foods")){
            arr = SingleInstance.getInstanceFood();
        }

        this.mContext = context;
        this.type = type;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_item, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        holder.textViewItem.setText(arr.get(position).getName());
        holder.textViewPrice.setText("Rp. " + arr.get(position).getPrice() + "");
        holder.button.setText("Order");
        holder.button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(mContext, ItemDetailActivity.class);
                i.putExtra("itemType" , type);
                i.putExtra("ItemArrayPosition", position);
                mContext.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return arr.size();
    }

    public static class MyViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textViewItem, textViewPrice;
        public Button button;
        public MyViewHolder(View view) {
            super(view);
            textViewItem = view.findViewById(R.id.textView_listTextItem);
            textViewPrice = view.findViewById(R.id.textView_listTextPrice);
            button = view.findViewById(R.id.button_listButton);
        }
    }

}
