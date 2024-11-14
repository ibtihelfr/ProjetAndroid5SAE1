package com.example.school;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
<<<<<<< HEAD

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.school.DAO.UserDAO;
import com.example.school.database.database;
import com.example.school.evenement.FormulaireEvent;
import com.example.school.evenement.listEvent;
=======
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.school.Classe.AddEditClasse;
import com.example.school.Classe.ListeClasse;
import com.example.school.ClasseAdapter.ClasseAdapter;
import com.example.school.DAO.ClasseDAO;
import com.example.school.DAO.UserDAO;
import com.example.school.database.database;
import com.example.school.models.Classe;
>>>>>>> 7c819f0 (crud classe)
import com.example.school.models.User;

public class MainActivity extends AppCompatActivity {
    private database db;
    private UserDAO userDAO;
<<<<<<< HEAD
    public static final int REQUEST_CODE_ADD_EDIT = 1;

=======
    private ClasseDAO classeDAO;
    private ClasseAdapter ClasseAdapter;
    public static final int REQUEST_CODE_ADD_EDIT = 1;
>>>>>>> 7c819f0 (crud classe)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        db = database.getInstance(this);
<<<<<<< HEAD
        userDAO = db.userDAO();

//        insertSampleData();


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
//    private void insertSampleData() {
//        new Thread(() -> {
//            User user = new User();
//            user.setLastName("Sample User"); // Set any necessary fields
//            user.setMail("sample@example.com");
//            userDAO.createUser(user);
//        }).start();
//    }
    public void add(View view) {
        Intent intent = new Intent(this, FormulaireEvent.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_EDIT);
    }
    public void move(View view) {
        Intent intent = new Intent(this, listEvent.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_EDIT);
    }

}
=======
      //  userDAO = db.userDAO();
        classeDAO=db.classeDAO();
//        insertSampleData();


    }
    private void insertSampleData() {
        new Thread(() -> {
            Classe user = new Classe();
            user.setNiveau("Sample User"); // Set any necessary fields
            user.setClassName("sample@example.com");
            classeDAO.createClasse(user);
        }).start();
    }


    public void add(View view) {
        Intent intent = new Intent(this, AddEditClasse.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_EDIT);
    }
    public void list(View view) {
        Intent intent = new Intent(this, ListeClasse.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_EDIT);
    }

}
>>>>>>> 7c819f0 (crud classe)
