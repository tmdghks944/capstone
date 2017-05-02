package com.example.android.capstone;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class CosmeticsList extends ArrayAdapter<Cosmetics>{
    private Activity context;
    List<Cosmetics> cosmetics;

    public CosmeticsList(Activity context, List<Cosmetics> cosmetics) {
        super(context, R.layout.layout_cosmetics_list, cosmetics);
        this.context = context;
        this.cosmetics = cosmetics;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_cosmetics_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewAddress = (TextView) listViewItem.findViewById(R.id.textViewAddress);

        Cosmetics cosmetic = cosmetics.get(position);
        textViewName.setText(cosmetic.getCosmeticName());
        textViewAddress.setText(cosmetic.getCosmeticAddress());

        return listViewItem;
    }
}
