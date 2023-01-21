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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class login_page extends AppCompatActivity {
    final FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText email_address ,pass_word;

    ProgressBar progressBar;
    Button create_btn ,login_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_page);




        email_address = findViewById(R.id.email_address);
        pass_word = findViewById(R.id.pass_word);
        create_btn = findViewById(R.id.create_btn);
        login_btn = findViewById(R.id.login_btn);
        progressBar = findViewById(R.id.progress);

        login_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                login();
                progressBar.setVisibility(View.VISIBLE);
            }
        });

        create_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), register_page.class));
            }
        });
    }

    private void login() {

        String email = email_address.getText().toString().trim();
        String password = pass_word.getText().toString().trim();


        mAuth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful()){
                    Toast.makeText(login_page.this, "welcome", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),home_page.class));
                    finish();
                    progressBar.setVisibility(View.VISIBLE);
                }
                else {
                    Toast.makeText(login_page.this, "invalid account", Toast.LENGTH_SHORT).show();
                    startActivity(new Intent(getApplicationContext(),login_page.class));
                    finish();
                    progressBar.setVisibility(View.INVISIBLE);
                }
            }
        });
    }
}