package com.perform_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class home_page extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);

        bottom_navigation = findViewById(R.id.bottom_navigation_users);
        bottom_navigation.setSelectedItemId(R.id.home_id);

        bottom_navigation.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {

                switch (item.getItemId()) {
                    case R.id.home_id:

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
                        startActivity(new Intent(getApplicationContext(), profile_page.class));
                        overridePendingTransition(0, 0);
                        finish();
                        return true;
                }
                return false;
            }
        });
    }
}