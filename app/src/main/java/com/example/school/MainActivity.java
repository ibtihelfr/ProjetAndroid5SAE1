package com.example.school;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import androidx.recyclerview.widget.RecyclerView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.school.DAO.ExamenDAO;
import com.example.school.DAO.ExamenDAO;
import com.example.school.Exam.AddExam;
import com.example.school.Exam.AfficheExamen;
import com.example.school.adapter.ExamenAdapter;
import com.example.school.database.database;
import com.example.school.models.Examen;
import com.example.school.models.User;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;




public class MainActivity extends AppCompatActivity {
    private database db;
    private ExamenDAO examenDAO;
    public static final int REQUEST_CODE_ADD_EDIT = 1;
    private RecyclerView recyclerView;
    private TextView noeventtext;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);


        db = database.getInstance(this);
        examenDAO = db.examenDAO();

//        insertSampleData();



    }

private void insertSampleData() {
    new Thread(() -> {
        Examen user = new Examen();
        user.setType("Sample User"); // Set any necessary fields
        user.setMatiere("sample@example.com");
        examenDAO.createExamen(user);
    }).start();
}
public void add(View view) {
    Intent intent = new Intent(this, AddExam.class);
    startActivityForResult(intent, REQUEST_CODE_ADD_EDIT);
}
public void move(View view) {
    Intent intent = new Intent(this, AfficheExamen.class);
    startActivityForResult(intent, REQUEST_CODE_ADD_EDIT);
}

}
