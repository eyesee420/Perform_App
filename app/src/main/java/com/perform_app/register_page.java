package com.perform_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class register_page extends AppCompatActivity {
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText full_name,age,phone_number,email_address ,pass_word;
    ProgressBar progressBar;
    Button create_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_page);

        full_name = findViewById(R.id.full_name);
        age = findViewById(R.id.age);
        phone_number = findViewById(R.id.phone_number);
        email_address = findViewById(R.id.email_address);
        pass_word = findViewById(R.id.pass_word);
        create_btn = findViewById(R.id.create_btn);

        progressBar = findViewById(R.id.progress);

        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
                progressBar.setVisibility(View.VISIBLE);
            }
        });
    }

    private void createAccount() {

        String fullname = full_name.getText().toString().trim();
        String aged = age.getText().toString().trim();
        String phonenumber = phone_number.getText().toString().trim();
        String email = email_address.getText().toString().trim();
        String password = pass_word.getText().toString().trim();



        mAuth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    FirebaseUser userid = mAuth.getCurrentUser();
                    String doc_id = userid.getUid();
                    users_model usersModels = new users_model(fullname,aged,phonenumber,email,password,doc_id);
                    db.collection("USERS").document(userid.getUid())
                            .set(usersModels).addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(register_page.this, "Account Successful"
                                            , Toast.LENGTH_SHORT).show();
                                    progressBar.setVisibility(View.INVISIBLE);
                                    startActivity(new Intent(getApplicationContext(),login_page.class));
                                }
                            });
                }else{

                    startActivity(new Intent(getApplicationContext(),login_page.class));
                    Toast.makeText(register_page.this, "Account Failed Successfully"
                            , Toast.LENGTH_SHORT).show();

                    progressBar.setVisibility(View.INVISIBLE);

                }
            }
        });

    }
}