package com.example.binus_ezyfoody.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.binus_ezyfoody.EzFood.EzFood;
import com.example.binus_ezyfoody.R;
import com.example.binus_ezyfoody.Singleton.SingleInstance;

import java.util.ArrayList;

public class ListOrderAdapter extends RecyclerView.Adapter<ListOrderAdapter.OrderViewHolder> {
    Context mcontext = null;

    public ListOrderAdapter(Context mContext) {
        this.mcontext = mContext;
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.card_order, parent, false);
        return new OrderViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrderViewHolder holder, final int position) {
        init(holder, position);
        holder.clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(mcontext, SingleInstance.getInstanceCart().get(position).getName() + "had been clear", Toast.LENGTH_LONG).show();
                SingleInstance.getInstanceCart().remove(position);
                calculateTotal();
                notifyItemRemoved(position);
            }
        });
    }

    public void init(OrderViewHolder holder, int position){
        holder.textViewItem.setText(SingleInstance.getInstanceCart().get(position).getName());
        holder.textViewPrice.setText("Rp. " + SingleInstance.getInstanceCart().get(position).getPrice() + "");
        holder.textViewQuantity.setText("x" + SingleInstance.getInstanceCart().get(position).getQuantity() + "");
        int totalPrice = SingleInstance.getInstanceCart().get(position).getPrice() * SingleInstance.getInstanceCart().get(position).getQuantity();
        holder.textViewTotal.setText("Total Price: Rp. " + totalPrice + "");
        calculateTotal();
    }

    public void calculateTotal(){
        SingleInstance.totalOrderPrice = 0;
        for(int i = 0; i < getItemCount() ; i++){
            SingleInstance.totalOrderPrice += SingleInstance.getInstanceCart().get(i).getPrice() * SingleInstance.getInstanceCart().get(i).getQuantity();
        }
    }

    @Override
    public int getItemCount() {
        return SingleInstance.getInstanceCart().size();
    }

    public static class OrderViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public TextView textViewItem, textViewPrice, textViewQuantity, textViewTotal;
        public Button clear;
        public OrderViewHolder(View view) {
            super(view);
            textViewItem = view.findViewById(R.id.textView_orderTextItem);
            textViewPrice = view.findViewById(R.id.textView_orderTextPrice);
            textViewQuantity = view.findViewById(R.id.textView_orderTextQuantity);
            textViewTotal = view.findViewById(R.id.textView_orderTextTotalPrice);

            clear = view.findViewById(R.id.button_orderClearButton);
        }
    }
}
