package com.example.school.DAO;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;


import com.example.school.models.Reclamation;

import java.util.List;
@Dao
public interface ReclamationDAO {

    @Insert
    void createReclamation(Reclamation reclamation );
    @Delete
    void DeleteReclamation(Reclamation reclamation);
    @Update
    void updateReclamation(Reclamation reclamation);
    @Query("SELECT * FROM reclamation ")
    List<Reclamation> getAllReclamation();
    @Query("SELECT * FROM reclamation WHERE idRec = :id")
    Reclamation getReclamationById(int id);

}
