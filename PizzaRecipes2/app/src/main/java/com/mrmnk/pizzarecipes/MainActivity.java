package com.mrmnk.pizzarecipes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;


public class MainActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecyclerView.Adapter adapter;
    private RecyclerView.LayoutManager layoutManager;
    final String recipesFileName = "recipes.json";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<PizzaRecipeItem> pizzaRecipeItems = new ArrayList<>();
        fillRecipesArray(recipesFileName, pizzaRecipeItems);

        recyclerView = findViewById(R.id.recyclerView);
        adapter = new RecyclerViewAdapter(pizzaRecipeItems);
        layoutManager = new LinearLayoutManager(this);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    // Fills an ArrayList<PizzaRecipeItem> from a JSON file
    private void fillRecipesArray (String fileName, ArrayList<PizzaRecipeItem> pizzaRecipeItems) {
        try {
            JSONObject jObject = new JSONObject(readFile(fileName));
            JSONArray jArray = jObject.getJSONArray("pizzaRecipes");
            for (int i = 0; i < jArray.length(); i++){
                JSONObject recipeInfo = jArray.getJSONObject(i);
                pizzaRecipeItems.add(new PizzaRecipeItem(recipeInfo.getString("image"),
                        recipeInfo.getString("tittle"),
                        recipeInfo.getString("previewText"),
                        recipeInfo.getString("recipe")));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    // Reads the file and returns the result as a string
    private String readFile(String fileName){
        byte[] buffer = null;
        InputStream is;
        try {
            is = getAssets().open(fileName);
            int size = is.available();
            buffer = new byte[size];
            is.read(buffer);
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        String str_data = new String(buffer);
        return str_data;
    }

}
