package com.mrmnk.pizzarecipes;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.TextView;

public class RecipeActivity extends AppCompatActivity {

    private int imageResource;
    private String titleText;
    private String previewText;
    private String recipeBodyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe);

        Intent receivedIntent = getIntent();
        setTitle(receivedIntent.getStringExtra("title"));
        contentFiller(receivedIntent);
    }

    // Fill our TextViews and ImageView
    private void contentFiller (Intent receivedIntent){

        ImageView image = findViewById(R.id.pizzaImageView);
        image.setMaxHeight(image.getWidth());
        image.setImageResource(receivedIntent.getIntExtra("image", 0));

        TextView titleTextView = findViewById(R.id.titleTextView);
        titleTextView.setText(receivedIntent.getStringExtra("title"));

        TextView previewTextView = findViewById(R.id.previewTextView);
        previewTextView.setText(receivedIntent.getStringExtra("preview"));

        TextView bodyRecipeTextView = findViewById(R.id.recipeBodyTextView);
        bodyRecipeTextView.setText(receivedIntent.getStringExtra("body"));
    }
}
