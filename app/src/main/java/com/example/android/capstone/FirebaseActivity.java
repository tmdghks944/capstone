package com.example.android.capstone;


import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
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

    //view objects
    EditText editTextName;
    EditText editTextAddress;
    Button buttonAddArtist;
    //a list to store all the artist from firebase database
    List<Cosmetics> cosmetics;

    //our database reference object
    DatabaseReference databaseCosmetics;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_firebase);

        //getting the reference of artists node
        databaseCosmetics = FirebaseDatabase.getInstance().getReference("cosmetics");
        //getting views
        editTextName = (EditText) findViewById(R.id.editTextName);
        editTextAddress = (EditText)findViewById(R.id.editTextAddress);

        buttonAddArtist = (Button) findViewById(R.id.buttonSave);
        //list to store artists
        cosmetics = new ArrayList<>();

        //adding an onclicklistener to button
        buttonAddArtist.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //calling the method addArtist()
                //the method is defined below
                //this method is actually performing the write operation
                addArtist();
            }
        });
    }

    /*
    * This method is saving a new artist to the
    * Firebase Realtime Database
    * */
    private void addArtist() {
        //getting the values to save
        String name = editTextName.getText().toString().trim();
        String address = editTextAddress.getText().toString().trim();

        //checking if the value is provided
        if (!TextUtils.isEmpty(name)) {

            //getting a unique id using push().getKey() method
            //it will create a unique id and we will use it as the Primary Key for our Artist
            String id = databaseCosmetics.push().getKey();
            //creating an Artist Object
            Cosmetics cosmetic = new Cosmetics(id, name, address);

            //Saving the Artist
            databaseCosmetics.child(id).setValue(cosmetic);

            //displaying a success toast
            Toast.makeText(this, "Cosmetics added", Toast.LENGTH_LONG).show();
        } else {
            //if the value is not given displaying a toast
            Toast.makeText(this, "Please enter a name", Toast.LENGTH_LONG).show();
        }
    }
}