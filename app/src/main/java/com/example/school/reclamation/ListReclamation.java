package com.example.school.reclamation;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.school.R;
import com.example.school.adapters.ReclamationAdapter;
import com.example.school.models.Reclamation;
import com.example.school.database.database;
import com.example.school.DAO.ReclamationDAO;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListReclamation extends AppCompatActivity {

    private ReclamationDAO reclamationDAO;
    private database db;
    private RecyclerView recyclerView;
    private ReclamationAdapter reclamationAdapter;
    private TextView noeventtext;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    public static final int REQUEST_CODE_ADD_EDIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_reclamation);

        db = database.getInstance(this);
        reclamationDAO = db.reclamationDAO();

        recyclerView = findViewById(R.id.recycler_view);
        noeventtext = findViewById(R.id.no_event_text);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadEvents(); // Charger les événements au lancement
    }

    private void loadEvents() {
        executorService.execute(() -> {
            List<Reclamation> evenementList = reclamationDAO.getAllReclamation();
            runOnUiThread(() -> {
                if (evenementList.isEmpty()) {
                    noeventtext.setVisibility(TextView.VISIBLE);
                } else {
                    noeventtext.setVisibility(TextView.GONE);
                    reclamationAdapter = new ReclamationAdapter(evenementList, ListReclamation.this);
                    recyclerView.setAdapter(reclamationAdapter);
                }
            });
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            loadEvents(); // Recharger les événements après modification/ajout
        }
    }
}