package com.example.android.capstone;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class CosmeticActivity extends AppCompatActivity {

    Button buttonAddIngredient;
    EditText editTextIngredientName;
    SeekBar seekBarDanger;
    TextView textViewDanger, textViewCosmetic;
    ListView listViewIngredients;

    DatabaseReference databaseIngredients;

    List<Ingredient> ingredients;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cosmetic);

        Intent intent = getIntent();

        /*
        * this line is important
        * this time we are not getting the reference of a direct node
        * but inside the node ingredient we are creating a new child with the cosmetic id
        * and inside that node we will store all the ingredients with unique ids
        * */
        databaseIngredients = FirebaseDatabase.getInstance().getReference("ingredients").child(intent.getStringExtra(FirebaseActivity.COSMETIC_ID));

        buttonAddIngredient = (Button) findViewById(R.id.buttonAddIngredient);
        editTextIngredientName = (EditText) findViewById(R.id.editTextName);
        seekBarDanger = (SeekBar) findViewById(R.id.seekBarDanger);
        textViewDanger = (TextView) findViewById(R.id.textViewDanger);
        textViewCosmetic = (TextView) findViewById(R.id.textViewCosmetic);
        listViewIngredients = (ListView) findViewById(R.id.listViewIngredients);

        ingredients = new ArrayList<>();

        textViewCosmetic.setText(intent.getStringExtra(FirebaseActivity.COSMETIC_NAME));

        seekBarDanger.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener(){
            @Override
            public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
                textViewDanger.setText(String.valueOf(i));
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        buttonAddIngredient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                saveIngredient();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();

        databaseIngredients.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                ingredients.clear();
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    Ingredient ingredient = postSnapshot.getValue(Ingredient.class);
                    ingredients.add(ingredient);
                }
                IngredientList ingredientListAdapter = new IngredientList(CosmeticActivity.this, ingredients);
                listViewIngredients.setAdapter(ingredientListAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void saveIngredient() {
        String ingredientName = editTextIngredientName.getText().toString().trim();
        int danger = seekBarDanger.getProgress();
        if (!TextUtils.isEmpty(ingredientName)) {
            String id  = databaseIngredients.push().getKey();
            Ingredient ingredient = new Ingredient(id, ingredientName, danger);
            databaseIngredients.child(id).setValue(ingredient);
            Toast.makeText(this, "Ingredient saved", Toast.LENGTH_LONG).show();
            editTextIngredientName.setText("");
        } else {
            Toast.makeText(this, "Please enter ingredient name", Toast.LENGTH_LONG).show();
        }
    }
}
