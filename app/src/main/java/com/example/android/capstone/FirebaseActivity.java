package com.example.android.capstone;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FirebaseActivity extends AppCompatActivity {
    public static final String COSMETIC_NAME = "com.example.android.capstone.cosmeticname";
    public static final String COSMETIC_ID = "com.example.android.capstone.cosmeticid";

    EditText editTextName;
    EditText editTextAddress;
    Button buttonAddCosmetic;
    ListView listViewCosmetics;

    //a list to store all the cosmetic from firebase database
    List<Cosmetic> cosmetics;

    //our database reference object
    DatabaseReference databaseCosmetics;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        //getting the reference of cosmetics node
        databaseCosmetics = FirebaseDatabase.getInstance().getReference("cosmetics");

        //getting views
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAddress = (EditText) findViewById(R.id.editTextAddress);
        listViewCosmetics = (ListView) findViewById(R.id.listViewCosmetics);

        buttonAddCosmetic = (Button) findViewById(R.id.buttonAddCosmetic);

        //list to store cosmetics
        cosmetics = new ArrayList<>();


        //adding an onclicklistener to button
        buttonAddCosmetic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addCosmetic()
                //the method is defined below
                //this method is actually performing the write operation
                addCosmetic();
            }
        });

        //attaching listener to listview
        listViewCosmetics.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                //getting the selected cosmetic
                Cosmetic cosmetic = cosmetics.get(i);

                //creating an intent
                Intent intent = new Intent(getApplicationContext(), CosmeticActivity.class);

                //putting cosmetic name and id to intent
                intent.putExtra(COSMETIC_ID, cosmetic.getCosmeticId());
                intent.putExtra(COSMETIC_NAME, cosmetic.getCosmeticName());

                //starting the activity with intent
                startActivity(intent);
            }
        });

        listViewCosmetics.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                Cosmetic cosmetic = cosmetics.get(i);
                showUpdateDeleteDialog(cosmetic.getCosmeticId(), cosmetic.getCosmeticName());
                return true;
            }
        });


    }

    private void showUpdateDeleteDialog(final String cosmeticId, String cosmeticName) {

        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        LayoutInflater inflater = getLayoutInflater();
        final View dialogView = inflater.inflate(R.layout.update_dialog, null);
        dialogBuilder.setView(dialogView);

        final EditText editTextName = (EditText) dialogView.findViewById(R.id.editTextName);
        final EditText editTextAddress = (EditText) dialogView.findViewById(R.id.editTextAddress);
        final Button buttonUpdate = (Button) dialogView.findViewById(R.id.buttonUpdateCosmetic);
        final Button buttonDelete = (Button) dialogView.findViewById(R.id.buttonDeleteCosmetic);

        dialogBuilder.setTitle(cosmeticName);
        final AlertDialog b = dialogBuilder.create();
        b.show();

        buttonUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = editTextName.getText().toString().trim();
                String address = editTextAddress.getText().toString().trim();
                if (!TextUtils.isEmpty(name)) {
                    updateCosmetic(cosmeticId, name, address);
                    b.dismiss();
                }
            }
        });

        buttonDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                deleteCosmetic(cosmeticId);
                b.dismiss();
            }
        });
    }

    private boolean updateCosmetic(String id, String name, String address) {
        //getting the specified cosmetic reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("cosmetics").child(id);

        //updating cosmetic
        Cosmetic cosmetic = new Cosmetic(id, name, address);
        dR.setValue(cosmetic);
        Toast.makeText(getApplicationContext(), "Cosmetic Updated", Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean deleteCosmetic(String id) {
        //getting the specified cosmetic reference
        DatabaseReference dR = FirebaseDatabase.getInstance().getReference("cosmetics").child(id);

        //removing cosmetic
        dR.removeValue();

        //getting the ingredientss reference for the specified cosmetic
        DatabaseReference drIngredients = FirebaseDatabase.getInstance().getReference("ingredients").child(id);

        //removing all ingredients
        drIngredients.removeValue();
        Toast.makeText(getApplicationContext(), "Cosmetic Deleted", Toast.LENGTH_LONG).show();

        return true;
    }

    @Override
    protected void onStart() {
        super.onStart();
        //attaching value event listener
        databaseCosmetics.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                //clearing the previous cosmetic list
                cosmetics.clear();

                //iterating through all the nodes
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {
                    //getting cosmetic
                    Cosmetic cosmetic = postSnapshot.getValue(Cosmetic.class);
                    //adding cosmetic to the list
                    cosmetics.add(cosmetic);
                }

                //creating adapter
                CosmeticList cosmeticAdapter = new CosmeticList(FirebaseActivity.this, cosmetics);
                //attaching adapter to the listview
                listViewCosmetics.setAdapter(cosmeticAdapter);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


    /*
    * This method is saving a new cosmetic to the
    * Firebase Realtime Database
    * */
    private void addCosmetic() {
        //getting the values to save
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Cosmetic
            String id = databaseCosmetics.push().getKey();

            //creating an Cosmetic Object
            Cosmetic cosmetic = new Cosmetic(id, name, address);

            //Saving the Cosmetic
            databaseCosmetics.child(id).setValue(cosmetic);

            //setting edittext to blank again
            editTextName.setText("");

            //displaying a success toast
            Toast.makeText(this, "Cosmetic added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}
