package com.example.school.Classe;


import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.school.DAO.ClasseDAO;
import com.example.school.R;
import com.example.school.models.Classe;
import com.example.school.database.database;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class AddEditClasse  extends AppCompatActivity {

    private EditText niveauEditText, classNameEditText, emploiEditText;
    private Button saveButton;
    private TextView titleAction;

    private boolean isEditMode;
    private database db;
    private ClasseDAO classeDao;
    private int IdClasse;

    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_classe);

        // Initializing views
        niveauEditText = findViewById(R.id.niveau_input);
        classNameEditText = findViewById(R.id.class_name_input);
        emploiEditText = findViewById(R.id.emploi_input);
        saveButton = findViewById(R.id.add_class_button);
        titleAction = findViewById(R.id.titleAction);

        // Initialize database
        db = database.getInstance(this); // Assuming getInstance is the method for singleton access
        classeDao = db.classeDAO();

        Intent intent = getIntent();
        if (intent.hasExtra("IdClasse")) {
            IdClasse = intent.getIntExtra("IdClasse", -1);
            new Thread(() -> {
                Classe classe = classeDao.getClasseById(IdClasse);
                runOnUiThread(() -> {
                    if (classe != null) {
                        niveauEditText.setText(classe.getNiveau());
                        classNameEditText.setText(classe.getClassName());
                        emploiEditText.setText(classe.getEmploi());
                    }
                });
            }).start();
            isEditMode = true;
            titleAction.setText("Edit classe");
        } else {
            isEditMode = false;
            titleAction.setText("Add classe");
        }

        saveButton.setOnClickListener(v -> saveClasse());
    }


    private void saveClasse() {
        String niveau = niveauEditText.getText().toString().trim();
        String className = classNameEditText.getText().toString().trim();
        String emploi = emploiEditText.getText().toString().trim();

        if (niveau.isEmpty() || className.isEmpty() || emploi.isEmpty()) {
            Toast.makeText(this, "Veuillez remplir tous les champs", Toast.LENGTH_SHORT).show();
            return;
        }

        Classe classe = new Classe(niveau, className, emploi);
        new Thread(() -> {
            if (isEditMode) {
                classe.setIdClass(IdClasse);
                db.classeDAO().updateClasse(classe);
                runOnUiThread(() -> {
                    Toast.makeText(this, "classe updated successfully", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                });
            } else {
                db.classeDAO().createClasse(classe);
                runOnUiThread(() -> {
                    Toast.makeText(this, "classe added successfully", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                });
            }
        }).start();
    }
}