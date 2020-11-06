package com.mrmnk.pizzarecipes;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.RecyclerViewViewHolder> {

    private ArrayList<PizzaRecipeItem> arrayList;

    public static class RecyclerViewViewHolder extends RecyclerView.ViewHolder {

        public ImageView pizzaImage;
        public TextView titleRecipeTextView;
        public TextView previewRecipeTextView;
        public String bodyRecipeText;

        public RecyclerViewViewHolder(@NonNull View itemView) {
            super(itemView);
            pizzaImage = itemView.findViewById(R.id.pizzaImage);
            titleRecipeTextView = itemView.findViewById(R.id.titleRecipeTextView);
            previewRecipeTextView = itemView.findViewById(R.id.previewRecipeTextView);
        }
    }

    public RecyclerViewAdapter(ArrayList<PizzaRecipeItem> arrayList){
        this.arrayList = arrayList;
    }

    @NonNull
    @Override
    public RecyclerViewAdapter.RecyclerViewViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycler_view_item,
                parent, false);
        RecyclerViewViewHolder recyclerViewViewHolder = new RecyclerViewViewHolder(view);
        return recyclerViewViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewAdapter.RecyclerViewViewHolder holder, int position) {
        PizzaRecipeItem pizzaRecipeItem = arrayList.get(position);
        holder.pizzaImage.setImageResource(pizzaRecipeItem.getImageResource());
        holder.titleRecipeTextView.setText(pizzaRecipeItem.getTitleText());
        holder.previewRecipeTextView.setText(pizzaRecipeItem.getPreviewText());
        holder.bodyRecipeText = pizzaRecipeItem.getRecipeBodyText();
    }

    @Override
    public int getItemCount() {
        return arrayList.size();
    }


}
