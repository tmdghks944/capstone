package com.example.android.capstone;

import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.Transaction;
import com.google.firebase.database.ValueEventListener;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.v7.widget.helper.ItemTouchHelper;
import com.example.android.capstone.data.DetaillistContract;
import com.example.android.capstone.data.DetaillistDbHelper;

import org.w3c.dom.Text;

import java.util.HashMap;

public class ResultActivity extends AppCompatActivity {

    private DetailListAdapter mAdapter;
    private SQLiteDatabase mDb1;
    private TextView textView;
    private HashMap<String,Integer> warningmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);
        final GlobalVariable globalvariable = (GlobalVariable)getApplication();
        warningmap = globalvariable.getwarn();
        RecyclerView detailRecyclerView;

        detailRecyclerView = (RecyclerView) this.findViewById(R.id.all_details_list_view);
        detailRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        DetaillistDbHelper dbHelper = new DetaillistDbHelper(this);

        mDb1 = dbHelper.getWritableDatabase();
        mDb1.delete("detaillist",null,null);

        Cursor cursor = getAllGuests();
        mAdapter = new DetailListAdapter(this, cursor);
        detailRecyclerView.setAdapter(mAdapter);

        final String[] targetid = {new String()};
        String targetname = globalvariable.getdetailname();
        textView = (TextView)findViewById(R.id.Detailtext);
        textView.setText(targetname + "의 세부성분입니다.");
        detailRecyclerView.addItemDecoration(new DividerItemDecoration(ResultActivity.this,
                DividerItemDecoration.VERTICAL));

        detailRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), detailRecyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                mAdapter.swapCursor(getAllGuests());
            }

            @Override
            public void onLongClick(View view, int position) {

            }
        }));

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("cosmetics").orderByChild("cosmeticName").equalTo(targetname);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot targetcosmetic : dataSnapshot.getChildren()) {
                        Cosmetic newcosmetic = targetcosmetic.getValue(Cosmetic.class);
                        targetid[0] = newcosmetic.getCosmeticId();
                        Query query2 = reference.child("ingredients").child(targetid[0]);
                        query2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    for (DataSnapshot allingredients : dataSnapshot.getChildren()) {
                                        Ingredient newingredient = allingredients.getValue(Ingredient.class);
                                        if(warningmap.containsKey(newingredient.getIngredientName())){//해당 성분이 유해하면 하이라이팅
                                            globalvariable.sethighright(true);
                                        }
                                        else{
                                            globalvariable.sethighright(false);
                                        }
                                        addToWaitlist(newingredient.getIngredientName(),newingredient.getIngredientDanger());
                                    }
                                }
                            }
                            @Override
                            public void onCancelled(DatabaseError databaseError) {

                            }
                        });
                    }
                }
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        //해당하는 아이디를 찾았으면 그 화장품의 성분을 나열.



    }

    public void addToWaitlist(String name, int sum) {
        addNewGuest(name, sum);
        mAdapter.swapCursor(getAllGuests());
    }

    private Cursor getAllGuests() {
        return mDb1.query(
                DetaillistContract.DetaillistEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                DetaillistContract.DetaillistEntry.COLUMN_TIMESTAMP
        );
    }

    private long addNewGuest(String name, int partySize) {
        ContentValues cv = new ContentValues();
        cv.put(DetaillistContract.DetaillistEntry.COLUMN_DETAIL_NAME, name);
        cv.put(DetaillistContract.DetaillistEntry.COLUMN_DETAIL_SIZE, partySize);
        return mDb1.insert(DetaillistContract.DetaillistEntry.TABLE_NAME, null, cv);
    }

    private boolean removeGuest(long id) {
        // COMPLETED (2) Inside, call mDb.delete to pass in the TABLE_NAME and the condition that WaitlistEntry._ID equals id
        return mDb1.delete(DetaillistContract.DetaillistEntry.TABLE_NAME, DetaillistContract.DetaillistEntry._ID + "=" + id, null) > 0;
    }

}