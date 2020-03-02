package com.example.fireapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    FirebaseDatabase firebaseDatabase ;
    DatabaseReference ref;
    EditText name,phone,email;
    Button submit;
    int maxid = 0;
    Member member;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name= findViewById(R.id.nameId);
        phone = findViewById(R.id.phnId);
        email= findViewById(R.id.emailId);
        submit = findViewById(R.id.submitId);

        member = new Member();

        // Data Read
        ref= firebaseDatabase.getInstance().getReference().child("User")  ;

// data send and method call
         ref.addValueEventListener(new ValueEventListener() {
        @Override
        //data change
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {


            //
            if(dataSnapshot.exists()){

                // same value to give not change to value ( increment)  other override
                maxid = (int) dataSnapshot.getChildrenCount();


            }
            else{



            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {

        }
    });


         // button click and implementation 
    submit.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            member.setName(name.getText().toString());
            member.setPhone(phone.getText().toString());
            member.setEmail(email.getText().toString());

            ref.child(String.valueOf(maxid + 1)).setValue(member);
            Toast.makeText(MainActivity.this, "Submit Okay", Toast.LENGTH_SHORT).show();

        }
    });

    }
}
