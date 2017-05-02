package com.example.android.capstone;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties

public class Ingredient {
    private String IngredientId;
    private String IngredientName;
    private int IngredientDanger;

    public Ingredient() {

    }

    public Ingredient(String ingredientId, String ingredientName, int ingredientDanger) {
        this.IngredientName = ingredientName;
        this.IngredientDanger = ingredientDanger;
        this.IngredientId = ingredientId;
    }

    public String getIngredientName() {
        return IngredientName;
    }
    public int getIngredientDanger() {
        return IngredientDanger;
    }
}
