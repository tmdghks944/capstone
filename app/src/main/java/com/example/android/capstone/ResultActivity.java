package com.example.android.capstone;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.widget.helper.ItemTouchHelper;

public class ResultActivity extends AppCompatActivity {
//
//    private DetaillistAdapter mAdapter;
//    private SQLiteDatabase mDb1;
//
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
    }
//
//        GlobalVariable globalvariable = (GlobalVariable)getApplication();
//        RecyclerView detailRecyclerView;
//        detailRecyclerView = (RecyclerView) this.findViewById(R.id.all_details_list_view);
//        detailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
//        DetaillistDbHelper dbHelper = new DetaillistDbHelper(this);
//
//        mDb1 = dbHelper.getWritableDatabase();
//        mDb1.delete("detaillist",null,null);
//        Cursor cursor = getAllGuests();
//        mAdapter = new DetaillistAdapter(this, cursor);
//        detailRecyclerView.setAdapter(mAdapter);
//        String targetid = globalvariable.getDetailid();
//        Toast.makeText(ResultActivity.this,targetid,Toast.LENGTH_SHORT).show();
//
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//
//            // COMPLETED (4) Override onMove and simply return false inside
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
//                long id = (long) viewHolder.itemView.getTag();
//                mAdapter.swapCursor(getAllGuests());
//            }
//        }).attachToRecyclerView(detailRecyclerView);
//
//        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
//        Query query = reference.child("ingredients").child(targetid);
//        query.addListenerForSingleValueEvent(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                if (dataSnapshot.exists()) {
//                    for (DataSnapshot allingredients : dataSnapshot.getChildren()) {
//                        Ingredient newingredient = allingredients.getValue(Ingredient.class);
//                        addNewGuest(newingredient.getIngredientName(),newingredient.getIngredientDanger());
//                    }
//                }
//            }
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });
//    }
//
//    private Cursor getAllGuests() {
//        return mDb1.query(
//                DetaillistContract.DetaillistEntry.TABLE_NAME,
//                null,
//                null,
//                null,
//                null,
//                null,
//                DetaillistContract.DetaillistEntry.COLUMN_TIMESTAMP
//        );
//    }
//
//    private long addNewGuest(String name, int partySize) {
//        ContentValues cv = new ContentValues();
//        cv.put(DetaillistContract.DetaillistEntry.COLUMN_GUEST_NAME, name);
//        cv.put(DetaillistContract.DetaillistEntry.COLUMN_PARTY_SIZE, partySize);
//        return mDb1.insert(DetaillistContract.DetaillistEntry.TABLE_NAME, null, cv);
//    }
//
//    private boolean removeGuest(long id) {
//        // COMPLETED (2) Inside, call mDb.delete to pass in the TABLE_NAME and the condition that WaitlistEntry._ID equals id
//        return mDb1.delete(DetaillistContract.DetaillistEntry.TABLE_NAME, DetaillistContract.DetaillistEntry._ID + "=" + id, null) > 0;
//    }

}