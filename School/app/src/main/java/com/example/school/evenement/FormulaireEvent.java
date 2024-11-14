package com.example.school.evenement;

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

import com.example.school.R;
import com.example.school.database.database;
import com.example.school.models.Evenement;

public class FormulaireEvent extends AppCompatActivity {

    private EditText Heure, Prix, Description, Type;
    private TextView titleAction;
    private Button btn_AjouterEvenement;
    private database db;
    private boolean isEditMode;
    private int evenementId;
    public static final int REQUEST_CODE_ADD_EDIT = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_formulaire_event);

         db = database.getInstance(this);
         Heure = findViewById(R.id.etHeure);
         Prix = findViewById(R.id.etPrix);
         Description = findViewById(R.id.etDescription);
         Type = findViewById(R.id.etType);
        titleAction = findViewById(R.id.titleAction);
         btn_AjouterEvenement = findViewById(R.id.btnAjouterEvenement);



        Intent intent = getIntent();


        if (intent.hasExtra("idEvent")) {
            evenementId = intent.getIntExtra("idEvent", -1);
            new Thread(() -> {
                Evenement evenement = db.evenementDAO().getEvenementById(evenementId);
                runOnUiThread(() -> {
                    Heure.setText(evenement.getHeure());
                    Prix.setText(String.valueOf(evenement.getPrix()));
                    Description.setText(evenement.getDescription());
                    Type.setText(evenement.getType());
                });
            }).start();
            isEditMode = true;
            titleAction.setText("Edit Event");
        } else {
            isEditMode = false;
            titleAction.setText("Add Event");
        }

        btn_AjouterEvenement.setOnClickListener(v -> saveEvenement());
    }

    private void saveEvenement() {
        String heure = Heure.getText().toString();
        String prixText = Prix.getText().toString();
        String description = Description.getText().toString();
        String type = Type.getText().toString();

        if (heure.isEmpty() || prixText.isEmpty() || description.isEmpty() || type.isEmpty()) {
            Toast.makeText(this, "There are empty fields", Toast.LENGTH_SHORT).show();
            return;
        }

        float prix = Float.parseFloat(prixText);
        Evenement evenement = new Evenement(heure, prix, description, type);

        new Thread(() -> {
            if (isEditMode) {
                evenement.setIdEvent(evenementId);
                db.evenementDAO().updateEvenement(evenement);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Event updated successfully", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                });
            } else {
                db.evenementDAO().createEvenement(evenement);
                runOnUiThread(() -> {
                    Toast.makeText(this, "Event added successfully", Toast.LENGTH_SHORT).show();
                    setResult(RESULT_OK);
                    finish();
                });
            }
        }).start();
    }
}