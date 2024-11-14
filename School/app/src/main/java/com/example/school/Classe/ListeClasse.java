package com.example.school.Classe;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.school.ClasseAdapter.ClasseAdapter;
import com.example.school.DAO.ClasseDAO;
import com.example.school.R;
import com.example.school.database.database;
import com.example.school.models.Classe;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ListeClasse extends AppCompatActivity {
    private ClasseDAO classDAO;
    private database db;
    private RecyclerView recyclerView;
    private ClasseAdapter classAdapter;
    private TextView noclasstext;
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    public static final int REQUEST_CODE_ADD_EDIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_liste_classe);

        db = database.getInstance(this);
        classDAO = db.classeDAO();

        recyclerView = findViewById(R.id.recycler_view);
        noclasstext = findViewById(R.id.no_class_text);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        loadClass();
    }

    private void loadClass() {
        executorService.execute(() -> {
            List<Classe> classeList = classDAO.getAllClasse();
            runOnUiThread(() -> {
                if (classeList.isEmpty()) {
                    noclasstext.setVisibility(TextView.VISIBLE);
                } else {
                    noclasstext.setVisibility(TextView.GONE);
                    classAdapter = new ClasseAdapter(classeList, ListeClasse.this);
                    recyclerView.setAdapter(classAdapter);
                }
            });
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == RESULT_OK) {
            loadClass();
        }
    }
}




