package com.example.android.capstone;

import android.content.ClipData;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.example.android.capstone.data.WaitlistContract;
import com.example.android.capstone.data.WaitlistDbHelper;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class Menu1Activity extends AppCompatActivity {
    private HashMap<String,Integer> warningmap;


    private GuestListAdapter mAdapter;
    private SQLiteDatabase mDb;

    private final static String LOG_TAG = Menu1Activity.class.getSimpleName();
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);

        final GlobalVariable globalvariable = (GlobalVariable)getApplication();
        warningmap = globalvariable.getwarn(); //WARNINGMAP에 사용자의 비추천 성분과 EWG가 들어있음.


        RecyclerView waitlistRecyclerView;

        waitlistRecyclerView = (RecyclerView) this.findViewById(R.id.all_guests_list_view);
        waitlistRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        WaitlistDbHelper dbHelper = new WaitlistDbHelper(this);

        mDb = dbHelper.getWritableDatabase();
        mDb.delete("waitlist",null,null);
        Cursor cursor = getAllGuests();

        mAdapter = new GuestListAdapter(this, cursor);
        waitlistRecyclerView.setAdapter(mAdapter);

        final String[] ids= new String[100];
        final String[] names = new String[100];

        final Map<String,Integer> map = new HashMap<String,Integer>();

        final Intent intent = new Intent(this,ResultActivity.class);

        waitlistRecyclerView.addOnItemTouchListener(new RecyclerViewTouchListener(getApplicationContext(), waitlistRecyclerView, new RecyclerViewClickListener() {
            @Override
            public void onClick(View view, int position) {
                globalvariable.setdetailid(ids[position]);
                globalvariable.setdetailname(names[position]);
                //removeGuest(id);
                mAdapter.swapCursor(getAllGuests());
                startActivity(intent);
            }

            @Override
            public void onLongClick(View view, int position) {
                Toast.makeText(getApplicationContext(), String.valueOf(position) + " is long pressed!", Toast.LENGTH_SHORT).show();

            }
        }));
//        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
//            @Override
//            public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
//                return false;
//            }
//
//            @Override
//            public void onSwiped(RecyclerView.ViewHolder viewHolder, int swipeDir) {
//                long id = (long) viewHolder.itemView.getTag();

//            }
//        }).attachToRecyclerView(waitlistRecyclerView);


        final int[] cosmeticsize={0};
        final int[] targetsize={0};
        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("cosmetics");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //allcosmetics에 모든 화장품이 들어가있음.
                    for (DataSnapshot allcosmetics : dataSnapshot.getChildren()) {
                        final int[] ewgsum = {0};
                        targetsize[0]=(int)dataSnapshot.getChildrenCount();
                        final Cosmetic newcosmetic = allcosmetics.getValue(Cosmetic.class);
                        Query query2 = reference.child("ingredients").child(newcosmetic.getCosmeticId());
                        query2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    ids[cosmeticsize[0]]=newcosmetic.getCosmeticId();
                                    names[cosmeticsize[0]]=newcosmetic.getCosmeticName();
                                    //allingredient에 각 화장품에 대한 모든 성분이 들어가있음.
                                    cosmeticsize[0]=cosmeticsize[0]+1;

                                    for (DataSnapshot allingredients : dataSnapshot.getChildren()) { //
                                        Ingredient newingredient = allingredients.getValue(Ingredient.class);
                                        if(warningmap.containsKey(newingredient.getIngredientName())){
                                            ewgsum[0] += warningmap.get(newingredient.getIngredientName()).intValue();
                                        }
                                    }
                                    //여기서 각 화장품에대한 처리가 이루어 져야한다. ewgsum[0]에 사용자의 비추천 성분의 ewg합이 저장된다.
                                    map.put(newcosmetic.getCosmeticName(),Integer.valueOf(ewgsum[0]));
                                    ewgsum[0]=0;

                                    if(targetsize[0] == cosmeticsize[0]) {
                                        Iterator it = sortByValue(map).iterator();
                                        while (it.hasNext()) {
                                            String temp = (String) it.next();
                                            addToWaitlist(temp, map.get(temp).intValue());
                                        }
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
    }

    public void addToWaitlist(String name, int sum) {
        addNewGuest(name, sum);
        mAdapter.swapCursor(getAllGuests());
    }

    private Cursor getAllGuests() {
        return mDb.query(
                WaitlistContract.WaitlistEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                WaitlistContract.WaitlistEntry.COLUMN_TIMESTAMP
        );
    }

    private long addNewGuest(String name, int partySize) {
        ContentValues cv = new ContentValues();
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_GUEST_NAME, name);
        cv.put(WaitlistContract.WaitlistEntry.COLUMN_PARTY_SIZE, partySize);
        return mDb.insert(WaitlistContract.WaitlistEntry.TABLE_NAME, null, cv);
    }

    private boolean removeGuest(long id) {
        // COMPLETED (2) Inside, call mDb.delete to pass in the TABLE_NAME and the condition that WaitlistEntry._ID equals id
        return mDb.delete(WaitlistContract.WaitlistEntry.TABLE_NAME, WaitlistContract.WaitlistEntry._ID + "=" + id, null) > 0;
    }

    public static List sortByValue(final Map map) {
        List<String> list = new ArrayList();
        list.addAll(map.keySet());

        Collections.sort(list,new Comparator() {

            public int compare(Object o1,Object o2) {
                Object v1 = map.get(o1);
                Object v2 = map.get(o2);

                return ((Comparable) v2).compareTo(v1);
            }

        });
        Collections.reverse(list);
        return list;
    }
}