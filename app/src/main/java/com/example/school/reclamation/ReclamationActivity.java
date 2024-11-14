package com.example.school.reclamation;
//
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.school.R;
import com.example.school.DAO.ReclamationDAO;
import com.example.school.database.database;
import com.example.school.models.Reclamation;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//public class ReclamationActivity extends AppCompatActivity {
//
//    private EditText messageEditText;
//    private ReclamationDAO reclamationDAO;
//    private database db;
//    private ExecutorService executorService;
//
//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_reclamation); // Use correct layout here
//
//        messageEditText = findViewById(R.id.message_id);
//
//        // Initialize database instance
//        db = database.getInstance(this);
//        reclamationDAO = db.reclamationDAO();  // Now db is initialized
//
//        // Initialize the ExecutorService
//        executorService = Executors.newSingleThreadExecutor();
//
//        findViewById(R.id.btn_env).setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                String message = messageEditText.getText().toString();
//
//                if (!message.isEmpty()) {
//                    // Log the message
//                    Log.d("test ", message);
//                    Reclamation reclamation = new Reclamation("Type", message, false);
//                    reclamation.setIdRec(22);
//
//                    // Log the reclamation object
//                    Log.d("test reclamationnnn", reclamation.toString());
//
//                    // Perform database operation on background thread
//                    executorService.execute(new Runnable() {
//                        @Override
//                        public void run() {
//                            // Insert the reclamation into the database
//                            db.reclamationDAO().createReclamation(reclamation);
//
//                            // Show the success Toast on the main UI thread
//                            runOnUiThread(new Runnable() {
//                                @Override
//                                public void run() {
//                                    Toast.makeText(ReclamationActivity.this, "Reclamation sent", Toast.LENGTH_SHORT).show();
//                                    messageEditText.setText(""); // Clear the EditText
//                                }
//                            });
//                        }
//                    });
//                } else {
//                    Toast.makeText(ReclamationActivity.this, "Message cannot be empty", Toast.LENGTH_SHORT).show();
//                }
//            }
//        });
//    }
//}
public class ReclamationActivity extends AppCompatActivity {
    public static final int REQUEST_CODE_ADD_EDIT = 1;


    private EditText messageEditText;
    private ReclamationDAO reclamationDAO;
    private database db;
    private ExecutorService executorService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reclamation); // Utiliser le bon layout ici

        messageEditText = findViewById(R.id.message_id);

        // Initialiser l'instance de la base de données
        db = database.getInstance(this);
        reclamationDAO = db.reclamationDAO();  // db doit maintenant être initialisé

        // Initialiser l'ExecutorService
        executorService = Executors.newSingleThreadExecutor();

        findViewById(R.id.btn_env).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String message = messageEditText.getText().toString();

                if (!message.isEmpty()) {
                    // Log du message
                    Log.d("test ", message);

                    // Créer la réclamation sans assigner un ID statique
                    Reclamation reclamation = new Reclamation("Type", message, false);

                    // Log de la réclamation
                    Log.d("test reclamationnnn", reclamation.toString());

                    // Exécuter l'opération de base de données sur un thread de fond
                    executorService.execute(new Runnable() {
                        @Override
                        public void run() {
                            // Insérer la réclamation dans la base de données
                            db.reclamationDAO().createReclamation(reclamation);

                            // Afficher un Toast sur le thread principal après l'insertion
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    Toast.makeText(ReclamationActivity.this, "Reclamation sent", Toast.LENGTH_SHORT).show();
                                    messageEditText.setText(""); // Réinitialiser le champ EditText
                                }
                            });
                        }
                    });
                } else {
                    Toast.makeText(ReclamationActivity.this, "Message cannot be empty", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
    public void move(View view) {
        // Créer une nouvelle intention pour démarrer ListReclamation
        Intent intent = new Intent(this, ListReclamation.class);
        startActivity(intent);  // Démarrer l'activité
    }


}
