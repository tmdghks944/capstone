package com.example.android.capstone;

import android.content.Context;
import android.database.Cursor;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.example.android.capstone.data.DetaillistContract;

public class DetailListAdapter extends RecyclerView.Adapter<DetailListAdapter.DetailViewHolder> {

    private Cursor mCursor;
    private Context mContext;

    public DetailListAdapter(Context context, Cursor cursor) {
        this.mContext = context;
        this.mCursor = cursor;
    }

    @Override
    public DetailViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Get the RecyclerView item layout
        LayoutInflater inflater = LayoutInflater.from(mContext);
        View view = inflater.inflate(R.layout.detail_list_item, parent, false);
        return new DetailViewHolder(view);
    }

    @Override
    public void onBindViewHolder(DetailViewHolder holder, int position) {

        if (!mCursor.moveToPosition(position))
            return;
        // Update the view holder with the information needed to display
        String name = mCursor.getString(mCursor.getColumnIndex(DetaillistContract.DetaillistEntry.COLUMN_DETAIL_NAME));
        int partySize = mCursor.getInt(mCursor.getColumnIndex(DetaillistContract.DetaillistEntry.COLUMN_DETAIL_SIZE));
        // COMPLETED (6) Retrieve the id from the cursor and
        long id = mCursor.getLong(mCursor.getColumnIndex(DetaillistContract.DetaillistEntry._ID));

        // Display the guest name
        holder.nameTextView.setText(name);

        // Display the party count
        holder.partySizeTextView.setText(String.valueOf(partySize));
        if(partySize>=0 && partySize<=2){
            holder.partySizeTextView.setBackgroundResource(R.drawable.circle);
        }
        else if(partySize>=3 && partySize<=6){
            holder.partySizeTextView.setBackgroundResource(R.drawable.circle1);
        }
        else{
            holder.partySizeTextView.setBackgroundResource(R.drawable.circle2);
        }

        // COMPLETED (7) Set the tag of the itemview in the holder to the id
        holder.itemView.setTag(id);
    }


    @Override
    public int getItemCount() {
        return mCursor.getCount();
    }

    public void swapCursor(Cursor newCursor) {
        // Always close the previous mCursor first
        if (mCursor != null) mCursor.close();
        mCursor = newCursor;
        if (newCursor != null) {
            // Force the RecyclerView to refresh
            this.notifyDataSetChanged();
        }
    }


    class DetailViewHolder extends RecyclerView.ViewHolder {
        // Will display the guest name
        TextView nameTextView;
        // Will display the party size number
        TextView partySizeTextView;
        public DetailViewHolder(View itemView) {
            super(itemView);
            nameTextView = (TextView) itemView.findViewById(R.id.detail_name_text_view);
            partySizeTextView = (TextView) itemView.findViewById(R.id.detail_party_size_text_view);
        }
    }
}