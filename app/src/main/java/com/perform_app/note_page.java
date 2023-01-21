package com.perform_app;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class note_page extends AppCompatActivity {
    BottomNavigationView bottom_navigation;
    FirebaseFirestore db = FirebaseFirestore.getInstance();
    planner_adapter adapter ;
    RecyclerView recyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_page);

        recyclerView = findViewById(R.id.recycler);
        bottom_navigation = findViewById(R.id.bottom_navigation_users);
        bottom_navigation.setSelectedItemId(R.id.notes_id);


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

        recycler();
    }

    private void recycler() {


        Query query = db.collection("planner");

        FirestoreRecyclerOptions<planner_model> options = new FirestoreRecyclerOptions.Builder<planner_model>()
                .setQuery(query, planner_model.class).build();


        adapter = new planner_adapter(options);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }
    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}