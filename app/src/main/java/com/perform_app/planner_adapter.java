package com.perform_app;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;

public class planner_adapter extends FirestoreRecyclerAdapter<planner_model, planner_adapter.holder> {

    public planner_adapter(@NonNull FirestoreRecyclerOptions<planner_model> options) {
        super(options);
    }

    @Override
    protected void onBindViewHolder(@NonNull planner_adapter.holder holder, int i, @NonNull planner_model model) {
        holder.bind(model);
    }

    @NonNull
    @Override
    public planner_adapter.holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_notes,parent,false);
        return  new planner_adapter.holder(view);
    }

    public class holder extends RecyclerView.ViewHolder {
        TextView date_id,Time_id, Tittle_id,notes_id;
        Button delete_btn, cancel_button;
        Context context;
        FirebaseFirestore db = FirebaseFirestore.getInstance();
        private FirebaseAuth mAuth = FirebaseAuth.getInstance();
        public holder(@NonNull View itemView) {
            super(itemView);

            context = itemView.getContext();
            date_id = itemView.findViewById(R.id.date_id);
            Time_id = itemView.findViewById(R.id.Time_id);
            Tittle_id = itemView.findViewById(R.id.Tittle_id);
            notes_id = itemView.findViewById(R.id.notes_id);
            delete_btn = itemView.findViewById(R.id.delete_btn);
            cancel_button = itemView.findViewById(R.id.cancel_button);


        }

        public void bind(planner_model model) {

            date_id.setText(model.getCalendar());
            Time_id.setText(model.getTime());
            Tittle_id.setText(model.getTittle());
            notes_id.setText(model.getNotes());

            FirebaseUser userId = mAuth.getCurrentUser();

            delete_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    db.collection("planner").document(model.getDoc_id())
                            .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void unused) {
                                    Toast.makeText(context, "Task Deleted", Toast.LENGTH_SHORT).show();
                                }
                            });
                }
            });


        }
    }
}
