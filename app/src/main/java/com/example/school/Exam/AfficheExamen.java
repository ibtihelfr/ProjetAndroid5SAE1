package com.example.school.Exam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.school.adapter.ExamenAdapter;
import com.example.school.database.database;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.school.DAO.ExamenDAO;
import com.example.school.R;
import com.example.school.models.Examen;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AfficheExamen extends AppCompatActivity {
    private ExamenDAO examenDAO;
    private database db;
    private RecyclerView recyclerView;
    private ExamenAdapter examenAdapter;
    private TextView noeventtext;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    public static final int REQUEST_CODE_ADD_EDIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_affiche_examen);

        db = database.getInstance(this);
        examenDAO = db.examenDAO();

        recyclerView = findViewById(R.id.recycler_view);
        noeventtext = findViewById(R.id.no_event_text);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadEvents(); // Charger les événements au lancement
    }

    private void loadEvents() {
        executorService.execute(() -> {
            List<Examen> evenementList = examenDAO.getAllExamen();
            runOnUiThread(() -> {
                if (evenementList.isEmpty()) {
                    noeventtext.setVisibility(TextView.VISIBLE);
                } else {
                    noeventtext.setVisibility(TextView.GONE);
                    examenAdapter = new ExamenAdapter(evenementList, AfficheExamen.this);
                    recyclerView.setAdapter(examenAdapter);
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
