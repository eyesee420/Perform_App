package com.perform_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class profile_page extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    EditText full_name , age , phone_number , email_address ;
    Button update_btn;
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_page);

        bottom_navigation = findViewById(R.id.bottom_navigation_users);
        bottom_navigation.setSelectedItemId(R.id.profile_id);


        full_name = findViewById(R.id.full_name);
        age = findViewById(R.id.age);
        phone_number = findViewById(R.id.phone_number);
        email_address = findViewById(R.id.email_address);
        update_btn = findViewById(R.id.update_btn);

        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home_id:
                        startActivity(new Intent(getApplicationContext(), home_page.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.notes_id:
                        startActivity(new Intent(getApplicationContext(), note_page.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.add_notes_id:
                        startActivity(new Intent(getApplicationContext(), add_note_page.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;

                    case R.id.profile_id:

                        return true;
                }
                return false;
            }
        });

        FirebaseUser userid = mAuth.getCurrentUser();
        DocumentReference docRef = db.collection("USERS").document(userid.getUid());
        docRef.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
            @Override
            public void onSuccess(DocumentSnapshot documentSnapshot) {
                users_model usersModels = documentSnapshot.toObject(users_model.class);
                if (usersModels != null) {

                    full_name = findViewById(R.id.full_name);
                    age = findViewById(R.id.age);
                    phone_number = findViewById(R.id.phone_number);
                    email_address = findViewById(R.id.email_address);



                    full_name.setText(usersModels.getFullname());
                    age.setText(usersModels.getAge());
                    phone_number.setText(usersModels.getPhonenumber());
                    email_address.setText(usersModels.getEmail());



                }
            }
        });


        update_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                update();
            }
        });
    }

    private void update() {

        String fullname = full_name.getText().toString().trim();
        String aged = age.getText().toString().trim();
        String phonenumber = phone_number.getText().toString().trim();
        String email = email_address.getText().toString().trim();

        Map<String, Object> user = new HashMap<>();
        user.put("fullname", fullname);
        user.put("age", aged);
        user.put("phonenumber", phonenumber);
        user.put("email", email);

        FirebaseUser user_ID = mAuth.getCurrentUser();
        db.collection("USERS").document(user_ID.getUid())
                .update(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(profile_page.this, "Account Updated", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(), profile_page.class));
                        finish();
                    }
                });
    }
    }
