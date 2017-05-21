package com.example.android.capstone;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class Menu1Activity extends AppCompatActivity {
    private HashMap<String,Integer> warningmap;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu1);
        GlobalVariable globalvariable = (GlobalVariable)getApplication();
        warningmap = globalvariable.getwarn(); //WARNINGMAP에 사용자의 비추천 성분과 EWG가 들어있음.

        final DatabaseReference reference = FirebaseDatabase.getInstance().getReference();
        Query query = reference.child("cosmetics");
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    //allcosmetics에 모든 화장품이 들어가있음.
                    for (DataSnapshot allcosmetics : dataSnapshot.getChildren()) {
                        final int[] ewgsum = {0};
                        Cosmetic newcosmetic = allcosmetics.getValue(Cosmetic.class);
                        Query query2 = reference.child("ingredients").child(newcosmetic.getCosmeticId());
                        query2.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(DataSnapshot dataSnapshot) {
                                if (dataSnapshot.exists()) {
                                    //allingredient에 각 화장품에 대한 모든 성분이 들어가있음.
                                    for (DataSnapshot allingredients : dataSnapshot.getChildren()) { //
                                        Ingredient newingredient = allingredients.getValue(Ingredient.class);
                                        Toast.makeText(Menu1Activity.this, newingredient.getIngredientName(), Toast.LENGTH_SHORT).show();
                                        if(warningmap.containsKey(newingredient.getIngredientName())){
                                            ewgsum[0] += warningmap.get(newingredient.getIngredientName()).intValue();
                                        }
                                    }
                                    //여기서 각 화장품에대한 처리가 이루어 져야한다. ewgsum[0]에 사용자의 비추천 성분의 ewg합이 저장된다.
                                    Toast.makeText(Menu1Activity.this,Integer.toString(ewgsum[0]),Toast.LENGTH_SHORT).show();
                                    ewgsum[0]=0;
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
}