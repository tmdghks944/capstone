package com.example.android.capstone;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class IngredientsList extends ArrayAdapter<Ingredients> {
    private Activity context;
    List<Ingredients> ingredients;

    public IngredientsList(Activity context, List<Ingredients> ingredients) {
        super(context, R.layout.layout_cosmetics_list, ingredients);
        this.context = context;
        this.ingredients = ingredients;
    }

}
