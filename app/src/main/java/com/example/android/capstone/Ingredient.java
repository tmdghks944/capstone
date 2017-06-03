package com.example.android.capstone;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Ingredient {
    private String ingredientId;
    private String ingredientName;
    private int ingredientDanger;

    public Ingredient() {
    }

    public Ingredient(String ingredientId, String ingredientName, int ingredientDanger) {
        this.ingredientName = ingredientName;
        this.ingredientDanger = ingredientDanger;
        this.ingredientId = ingredientId;
    }

    public String getIngredientName() {
        return ingredientName;
    }
    public int getIngredientDanger() {
        return ingredientDanger;
    }
}
