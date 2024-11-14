
package com.example.school.evenement;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.school.DAO.EvenementDAO;
import com.example.school.R;
import com.example.school.database.database;
import com.example.school.models.Evenement;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import com.example.school.adapter.EventAdapter;
public class listEvent extends AppCompatActivity {
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    private EvenementDAO evenementDAO;
    private database db;
    private RecyclerView recyclerView;
    private EventAdapter eventAdapter;
    private TextView noeventtext;
    public static final int REQUEST_CODE_ADD_EDIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_event);

        db = database.getInstance(this);
        evenementDAO = db.evenementDAO();

        recyclerView = findViewById(R.id.recycler_view);
        noeventtext = findViewById(R.id.no_event_text);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadEvents(); // Charger les événements au lancement
    }

    private void loadEvents() {
        executorService.execute(() -> {
            List<Evenement> evenementList = evenementDAO.getAllEvenement();
            runOnUiThread(() -> {
                if (evenementList.isEmpty()) {
                    noeventtext.setVisibility(TextView.VISIBLE);
                } else {
                    noeventtext.setVisibility(TextView.GONE);
                    eventAdapter = new EventAdapter(evenementList, listEvent.this);
                    recyclerView.setAdapter(eventAdapter);
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


    private void updateEvenementList(List<Evenement> evenementList) {
        if (evenementList.isEmpty()) {
            noeventtext.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        } else {
            noeventtext.setVisibility(View.GONE);
            recyclerView.setVisibility(View.VISIBLE);
            eventAdapter = new EventAdapter(evenementList,this);
            recyclerView.setAdapter(eventAdapter);
        }
    }
    public void addRestaurant(View view) {
        Intent intent = new Intent(this, FormulaireEvent.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_EDIT);
    }


}
