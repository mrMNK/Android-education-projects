package com.mrmnk.pizzarecipes;

import java.lang.reflect.Field;

public class PizzaRecipeItem {

    private int imageResource;
    private String titleText;
    private String previewText;
    private String recipeBodyText;

    public PizzaRecipeItem(String stringImageResource, String titleText, String previewText, String recipeBodyText) {
        this.imageResource = getResId(stringImageResource, R.drawable.class);
        this.titleText = titleText;
        this.previewText = previewText;
        this.recipeBodyText = recipeBodyText;
    }

    public int getImageResource() {
        return imageResource;
    }

    public String getTitleText() {
        return titleText;
    }

    public String getRecipeBodyText() {
        return recipeBodyText;
    }

    public String getPreviewText() {
        return previewText;
    }


    // To getting integer value of resource in R.drawable by String filename
    public static int getResId(String resName, Class<?> c) {

        try {
            Field idField = c.getDeclaredField(resName);
            return idField.getInt(idField);
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}
