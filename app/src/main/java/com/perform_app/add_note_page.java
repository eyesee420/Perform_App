package com.perform_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.DateFormat;
import java.util.Calendar;

public class add_note_page extends AppCompatActivity {
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    private FirebaseAuth mAuth = FirebaseAuth.getInstance();
    EditText calendar_id , time_id , tittle_id , notes_id;
    planner_model plannerModel = new planner_model();
    String am_pm;
    BottomNavigationView bottom_navigation;
    Button add_btn ,cancel_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_note_page);


        bottom_navigation = findViewById(R.id.bottom_navigation_users);
        bottom_navigation.setSelectedItemId(R.id.add_notes_id);


        calendar_id = findViewById(R.id.calendar_id);
        time_id = findViewById(R.id.time_id);
        tittle_id = findViewById(R.id.Tittle_id);
        notes_id = findViewById(R.id.notes_id);
        add_btn = findViewById(R.id.add_btn);
        cancel_button = findViewById(R.id.cancel_button);


        Calendar calendar = Calendar.getInstance();
        final  int year = calendar.get(Calendar.YEAR);
        final  int month = calendar.get(Calendar.MONTH);
        final  int day = calendar.get(Calendar.DAY_OF_MONTH);


        cancel_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(getApplicationContext(), add_note_page.class));
                finish();
            }
        });
        calendar_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(add_note_page.this,
                        new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int month, int day) {

                                month = month+1;
                                String date = day+"/"+month+"/"+year;
                                calendar_id.setText(date);
                            }
                        },year,month,day);
                dialog.show();
            }
        });

        time_id.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar calendar = Calendar.getInstance();
                final  int hour = calendar.get(Calendar.HOUR_OF_DAY);
                final  int minute = calendar.get(Calendar.MINUTE);
                final  int day = calendar.get(Calendar.DAY_OF_MONTH);

                TimePickerDialog dialog = new TimePickerDialog(add_note_page.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int hourofday, int min) {

                        if(hourofday >= 12){
                            am_pm = "PM";
                        }else{
                            am_pm = "AM";
                        }
                        time_id.setText(String.format("%02d:%02d",hourofday,min)+ " "+am_pm);
                        //time_id.setText(hourofday+" : "+min + " "+am_pm);
                    }
                },hour,minute,false );
                dialog.show();
            }
        });

        add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                adddData();

            }
        });

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

                        return true;

                    case R.id.profile_id:
                        startActivity(new Intent(getApplicationContext(), profile_page.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }
                return false;
            }
        });

    }

    private void adddData() {
        String tittle = tittle_id.getText().toString().trim();
        String notes = notes_id.getText().toString().trim();
        String time = time_id.getText().toString().trim();
        String date = calendar_id.getText().toString().trim();


        String doc_id = db.collection("planner").document().getId();
            plannerModel =  new planner_model(doc_id , tittle , notes , time , date );

            FirebaseUser userid = mAuth.getCurrentUser();

        db.collection("planner").document(doc_id).set(plannerModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(add_note_page.this, "Success", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),add_note_page.class));
                        finish();
                    }
                });




    }
}