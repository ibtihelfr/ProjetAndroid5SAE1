package com.example.school.Exam;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import com.example.school.database.database;

import com.example.school.R;
import com.example.school.models.Examen;

public class AddExam extends AppCompatActivity {
    private EditText type_input, semestre_input, matiere_input, date_input;
    private TextView titleAction;
    private Button saveButton;
    private database db;
    private boolean isEditMode;
    private int examId;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_exam);

        db = database.getInstance(this);
        type_input = findViewById(R.id.type_input);
        semestre_input = findViewById(R.id.semestre_input);
        matiere_input = findViewById(R.id.matiere_input);
        date_input = findViewById(R.id.date_input);
        saveButton = findViewById(R.id.save_button);
        titleAction = findViewById(R.id.titleAction);


        Intent intent = getIntent();
        if (intent.hasExtra("examId")) {
            examId = intent.getIntExtra("examId", -1);
            new Thread(() -> {
                Examen examen = db.examenDAO().getExamenById(examId);
                runOnUiThread(() -> {
                    type_input.setText(examen.getType());
                    semestre_input.setText(examen.getSemestre());
                    matiere_input.setText(examen.getMatiere());
                    date_input.setText(examen.getDate());
                });
            }).start();
            isEditMode = true;
            titleAction.setText("Edit Examen");
        } else {
            isEditMode = false;
            titleAction.setText("Add Examen");
        }

        saveButton.setOnClickListener(v -> saveExamen());
    }

    private void saveExamen() {
        String type = type_input.getText().toString();
        String matiere = matiere_input.getText().toString();
        String semestre = semestre_input.getText().toString();
        String date = date_input.getText().toString();


        Examen examen = new Examen(type, semestre, matiere, date);
        new Thread(() -> {
            if (isEditMode) {
                examen.setIdExamen(examId);
                db.examenDAO().updateExamen(examen);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Examen updated successfully", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                });
            } else {
                db.examenDAO().createExamen(examen);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Examen added successfully", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                });
            }
        }).start();
    }


}