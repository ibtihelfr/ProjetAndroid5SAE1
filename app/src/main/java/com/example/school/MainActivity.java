package com.example.school;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.school.DAO.ReclamationDAO;
import com.example.school.DAO.UserDAO;
import com.example.school.database.database;
import com.example.school.models.User;
import com.example.school.reclamation.ListReclamation;
import com.example.school.reclamation.ReclamationActivity;

public class MainActivity extends AppCompatActivity {
    private database db;
    private UserDAO userDAO;
    private ReclamationDAO reclamationDAO;
    public static final int REQUEST_CODE_ADD_EDIT = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        db = database.getInstance(this);
        userDAO = db.userDAO();

        insertSampleData();


//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.), (v, insets) -> {
//            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
//            return insets;
//        });
    }
    private void insertSampleData() {
        new Thread(() -> {
            User user = new User();
            user.setLastName("Sample User"); // Set any necessary fields
            user.setMail("sample@example.com");
            userDAO.createUser(user);
        }).start();
    }
    public void add(View view) {
        Intent intent = new Intent(this, ReclamationActivity.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_EDIT);
    }
    public void move(View view) {
        Intent intent = new Intent(this, ListReclamation.class);
        startActivityForResult(intent, REQUEST_CODE_ADD_EDIT);
    }


}