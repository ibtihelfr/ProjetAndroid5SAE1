package com.example.school.database;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.school.DAO.ClasseDAO;
import com.example.school.DAO.EvenementDAO;
import com.example.school.DAO.ExamenDAO;
import com.example.school.DAO.ReclamationDAO;
import com.example.school.DAO.UserDAO;
import com.example.school.models.Classe;
import com.example.school.models.Evenement;
import com.example.school.models.Examen;
import com.example.school.models.Reclamation;
import com.example.school.models.User;

@Database(entities = {User.class, Evenement.class, Examen.class, Reclamation.class, Classe.class}, version = 2)
public abstract class database  extends RoomDatabase {

    private static database instance;

    public abstract UserDAO userDAO();
    public abstract ClasseDAO classeDAO();
    public abstract EvenementDAO evenementDAO();
    public abstract ReclamationDAO reclamationDAO();
    public abstract ExamenDAO examenDAO();

//    public abstract ClasseDAO classeDAO();


    public static synchronized database getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                            database.class, "school_db")

                    .fallbackToDestructiveMigration()
                    .build();
        }
        return instance;
    }


}
