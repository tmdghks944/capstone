package com.example.android.capstone;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class IngredientList extends ArrayAdapter<Ingredient> {
    private Activity context;
    List<Ingredient> ingredients;

    public IngredientList(Activity context, List<Ingredient> ingredients) {
        super(context, R.layout.layout_cosmetic_list, ingredients);
        this.context = context;
        this.ingredients = ingredients;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_cosmetic_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewDanger = (TextView) listViewItem.findViewById(R.id.textViewAddress);

        Ingredient ingredient = ingredients.get(position);
        textViewName.setText(ingredient.getIngredientName());
        textViewDanger.setText(String.valueOf(ingredient.getIngredientDanger()));

        return listViewItem;
    }
}