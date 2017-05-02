package com.example.android.capstone;

import android.app.Activity;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;


public class CosmeticList extends ArrayAdapter<Cosmetic> {
    private Activity context;
    List<Cosmetic> cosmetics;

    public CosmeticList(Activity context, List<Cosmetic> cosmetics) {
        super(context, R.layout.layout_cosmetic_list, cosmetics);
        this.context = context;
        this.cosmetics = cosmetics;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.layout_cosmetic_list, null, true);

        TextView textViewName = (TextView) listViewItem.findViewById(R.id.textViewName);
        TextView textViewAddress= (TextView) listViewItem.findViewById(R.id.textViewAddress);

        Cosmetic cosmetic = cosmetics.get(position);
        textViewName.setText(cosmetic.getCosmeticName());
        textViewAddress.setText(cosmetic.getCosmeticAddress());

        return listViewItem;
    }
}