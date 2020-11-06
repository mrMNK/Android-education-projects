package com.mrmnk.pizzarecipes;

public class PizzaRecipeItem {

    private int imageResource;
    private String titleText;
    private String previewText;
    private String recipeBodyText;

    public PizzaRecipeItem(int imageResource, String titleText, String previewText, String recipeBodyText) {
        this.imageResource = imageResource;
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
}
